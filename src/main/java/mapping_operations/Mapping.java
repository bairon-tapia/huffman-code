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

    public static Map<Character, TreeNode> createMapSortedByKey(@NonNull final Map<Character, TreeNode> mapCharAsKey) {
        final var sortedMap = mapCharAsKey
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, TreeNode>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        return (Collections.unmodifiableMap(sortedMap));
    }

    public static Map<Character, TreeNode> createMapSortedByRoute(final @NonNull Map<Character, TreeNode> mapCharAsKey) {
        final var sortedMap = mapCharAsKey
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getRoute()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        return (Collections.unmodifiableMap(sortedMap));
    }

    public static Map<Character, TreeNode> createMapCharAsKey(@NonNull final String string) {
        final var mapCharAsKey = new HashMap<Character, TreeNode>();
        final int n = string.length();
        for (int i = 0; i < n; i++) {
            final char character = string.charAt(i);
            if (mapCharAsKey.containsKey(character)) {
                final TreeNode treeNode = mapCharAsKey.get(character);
                treeNode.updateFrequency();
            } else {
                final TreeNode treeNode = new TreeNode(character);
                mapCharAsKey.put(character, treeNode);
            }
        }
        return (createMapSortedByKey(mapCharAsKey));
    }

    public static Map<String, TreeNode> createMapRouteAsKey(@NonNull final Map<Character, TreeNode> mapCharAsKey) {
        final var mapRouteAsKey = mapCharAsKey
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getValue().getRoute(), Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return (Collections.unmodifiableMap(mapRouteAsKey));
    }

    public static Map<String, TreeNode> createMapFilteredByRoutes(@NonNull final Map<String, TreeNode> mapRouteAsKey) {
        final var filteredMap = mapRouteAsKey
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().length() >= BYTE_LENGTH)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        return (Collections.unmodifiableMap(filteredMap));
    }

    public static PriorityQueue<TreeNode> createPriorityQueue(@NonNull final Map<Character, TreeNode> mapCharAsKey) {
        return (new PriorityQueue<>(mapCharAsKey.values()));
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
