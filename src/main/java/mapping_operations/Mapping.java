package mapping_operations;

import static route_operations.RouteHelper.buildRoutes;

import java.util.*;
import java.util.stream.Collectors;

import lombok.NonNull;

import tree_node.TreeNode;

public final class Mapping {

    private static final char DEFAULT_CHARACTER = '\u0000';
    private static final int BYTE_LENGTH = 8;

    private Mapping() {
        throw new UnsupportedOperationException();
    }

    public static Map<Character, TreeNode<Character>> createMapCharacterAsKey(@NonNull final String string) {
        final Map<Character, TreeNode<Character>> mapCharacterAsKey = new HashMap<>();
        final int n = string.length();
        for (int i = 0; i < n; i++) {
            final char character = string.charAt(i);
            if (mapCharacterAsKey.containsKey(character)) {
                final TreeNode<Character> treeNode = mapCharacterAsKey.get(character);
                treeNode.updateFrequency();
            } else {
                final TreeNode<Character> treeNode = new TreeNode<>(character);
                mapCharacterAsKey.put(character, treeNode);
            }
        }
        return (Collections.unmodifiableMap(mapCharacterAsKey));
    }

    public static Map<String, TreeNode<Character>> createMapRouteAsKey(@NonNull final Map<Character,
            TreeNode<Character>> mapCharacterAsKey) {
        final Map<String, TreeNode<Character>> mapRouteAsKey = mapCharacterAsKey
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getValue().getRoute(), Map.Entry::getValue));
        return (Collections.unmodifiableMap(mapRouteAsKey));
    }

    public static Map<String, TreeNode<Character>> filterByRoutesLength(@NonNull final Map<String,
            TreeNode<Character>> mapRouteAsKey) {
        final Map<String, TreeNode<Character>> mapFilteredByRoutesLength = mapRouteAsKey
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().length() >= BYTE_LENGTH)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return (Collections.unmodifiableMap(mapFilteredByRoutesLength));
    }

    public static PriorityQueue<TreeNode<Character>> createPriorityQueue(@NonNull final Map<Character,
            TreeNode<Character>> mapCharacterAsKey) {
        final PriorityQueue<TreeNode<Character>> priorityQueue =
                new PriorityQueue<>(Comparator.comparing(TreeNode::getFrequency));
        priorityQueue.addAll(mapCharacterAsKey.values());
        return (priorityQueue);
    }

    public static TreeNode<Character> createHuffmanTree(@NonNull final PriorityQueue<TreeNode<Character>> priorityQueue) {
        while (priorityQueue.size() > 1) {
            final TreeNode<Character> leftNode = priorityQueue.remove();
            final TreeNode<Character> rightNode = priorityQueue.remove();
            final TreeNode<Character> parentNode = new TreeNode<>(DEFAULT_CHARACTER, leftNode, rightNode,
                    leftNode.getFrequency() + rightNode.getFrequency());
            priorityQueue.add(parentNode);
        }
        final TreeNode<Character> rootNode = priorityQueue.remove();
        buildRoutes(rootNode, "");
        return (rootNode);
    }
}
