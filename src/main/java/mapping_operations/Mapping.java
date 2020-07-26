package mapping_operations;

import static tree_node.TreeNode.buildRoutes;

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

    public static Map<Character, TreeNode> createSortedMapCharAsKey(@NonNull final Map<Character, TreeNode> mapCharacterAsKey) {
        return (mapCharacterAsKey
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, TreeNode>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new)));
    }

    public static Map<Character, TreeNode> createMapCharAsKey(@NonNull final String string) {
        final Map<Character, TreeNode> mapCharacterAsKey = new HashMap<>();
        final int n = string.length();
        for (int i = 0; i < n; i++) {
            final char character = string.charAt(i);
            if (mapCharacterAsKey.containsKey(character)) {
                final TreeNode treeNode = mapCharacterAsKey.get(character);
                treeNode.updateFrequency();
            } else {
                final TreeNode treeNode = new TreeNode(character);
                mapCharacterAsKey.put(character, treeNode);
            }
        }
        return (createSortedMapCharAsKey(mapCharacterAsKey));
    }

    public static Map<String, TreeNode> createMapRouteAsKey(@NonNull final Map<Character,
            TreeNode> mapCharacterAsKey) {
        return (mapCharacterAsKey
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getValue().getRoute(), Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new))
        );
    }

    public static Map<String, TreeNode> createMapFilteredByRoutes(@NonNull final Map<String,
            TreeNode> mapRouteAsKey) {
        return (mapRouteAsKey
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().length() >= BYTE_LENGTH)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new))
        );
    }

    public static PriorityQueue<TreeNode> createPriorityQueue(@NonNull final Map<Character,
            TreeNode> mapCharacterAsKey) {
        final PriorityQueue<TreeNode> priorityQueue =
                new PriorityQueue<>(Comparator.comparing(TreeNode::getFrequency));
        priorityQueue.addAll(mapCharacterAsKey.values());
        return (priorityQueue);
    }

    public static TreeNode createHuffmanTree(@NonNull final PriorityQueue<TreeNode> priorityQueue) {
        while (priorityQueue.size() > 1) {
            final TreeNode leftNode = priorityQueue.remove();
            final TreeNode rightNode = priorityQueue.remove();
            final TreeNode parentNode = new TreeNode(DEFAULT_CHARACTER, leftNode, rightNode,
                    leftNode.getFrequency() + rightNode.getFrequency());
            priorityQueue.add(parentNode);
        }
        final TreeNode rootNode = priorityQueue.remove();
        buildRoutes("", rootNode);
        return (rootNode);
    }
}
