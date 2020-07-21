package trees;

import java.util.Map;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mapping_operations.Mapping;
import tree_node.TreeNode;

class TreeTesting {

    private static final char DEFAULT_CHARACTER = '\u0000';

    private final String defaultString = "This is a test!";
    private final Map<Character, TreeNode> mapCharacterAsKey =
            Mapping.createMapCharacterAsKey(defaultString);
    private final PriorityQueue<TreeNode> priorityQueue = Mapping.createPriorityQueue(mapCharacterAsKey);
    private final TreeNode rootNode = Mapping.createHuffmanTree(priorityQueue);

    static boolean isFullTree(final TreeNode rootNode) {
        if (rootNode == null) {
            return (true);
        }
        if (rootNode.isLeaf()) {
            return (true);
        }
        if ((rootNode.getLeft() != null) && (rootNode.getRight() != null)) {
            return (isFullTree(rootNode.getLeft()) && isFullTree(rootNode.getRight()));
        }
        return (false);
    }

    static boolean internalNodesContainValues(final TreeNode rootNode) {
        if (rootNode == null) {
            return (true);
        }
        if (!rootNode.isLeaf()) {
            return (rootNode.getElement() != DEFAULT_CHARACTER);
        }
        return (leafNodesContainValues(rootNode.getLeft()) && leafNodesContainValues(rootNode.getRight()));
    }

    static boolean leafNodesContainValues(final TreeNode rootNode) {
        if (rootNode == null) {
            return (true);
        }
        if (rootNode.isLeaf()) {
            return (rootNode.getElement() != DEFAULT_CHARACTER);
        }
        return (leafNodesContainValues(rootNode.getLeft()) && leafNodesContainValues(rootNode.getRight()));
    }

    static boolean isSumTree(final TreeNode rootNode) {
        int leftFrequency = 0;
        int rightFrequency = 0;
        if (rootNode == null || rootNode.isLeaf()) {
            return (true);
        } else {
            if (rootNode.getLeft() != null) {
                leftFrequency = rootNode.getLeft().getFrequency();
            }
            if (rootNode.getRight() != null) {
                rightFrequency = rootNode.getRight().getFrequency();
            }
            return (rootNode.getFrequency() == leftFrequency + rightFrequency)
                    && (isSumTree(rootNode.getLeft()))
                    && (isSumTree(rootNode.getRight()));
        }
    }

    @Test
    void isValidHuffmanTree() {
        if (!isFullTree(rootNode)) {
            Assertions.fail("ERROR: The tree is not a huffman tree because it isn't a full tree");
        }
        if (internalNodesContainValues(rootNode)) {
            Assertions.fail("ERROR: The tree is not a huffman tree because at least one of its internal nodes stores " +
                    "information (contains a character other than the null character).");
        }
        if (!leafNodesContainValues(rootNode)) {
            Assertions.fail("ERROR: The tree is not a huffman tree because at least one of its leaf nodes doesn't " +
                    "store information (contains a null character).");
        }
        if (!isSumTree(rootNode)) {
            Assertions.fail("ERROR: The tree is not a huffman tree it isn't a sum tree.");
        }
    }

}
