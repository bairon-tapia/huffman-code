package trees;

import java.util.Map;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mapping_operations.Mapping;
import tree_node.TreeNode;

class TreeTesting {

    private static final char DEFAULT_CHARACTER = '\u0000';
    private static final String DEFAULT_STRING = "This is a test!";
    private static final Map<Character, TreeNode<Character>> CHAR_AS_KEY =
            Mapping.createMapCharacterAsKey(DEFAULT_STRING);
    private static final PriorityQueue<TreeNode<Character>> PRIORITY_QUEUE = Mapping.createPriorityQueue(CHAR_AS_KEY);
    private static final TreeNode<Character> ROOT_NODE = Mapping.createHuffmanTree(PRIORITY_QUEUE);

    static boolean isFullTree(final TreeNode<Character> rootNode) {
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

    static boolean internalNodesContainValues(final TreeNode<Character> rootNode) {
        if (rootNode == null) {
            return (true);
        }
        if (!rootNode.isLeaf()) {
            return (rootNode.getElement() != DEFAULT_CHARACTER);
        }
        return (leafNodesContainValues(rootNode.getLeft()) && leafNodesContainValues(rootNode.getRight()));
    }

    static boolean leafNodesContainValues(final TreeNode<Character> rootNode) {
        if (rootNode == null) {
            return (true);
        }
        if (rootNode.isLeaf()) {
            return (rootNode.getElement() != DEFAULT_CHARACTER);
        }
        return (leafNodesContainValues(rootNode.getLeft()) && leafNodesContainValues(rootNode.getRight()));
    }

    static boolean isSumTree(final TreeNode<Character> rootNode) {
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
        if (!isFullTree(ROOT_NODE)) {
            Assertions.fail("ERROR: The tree is not a huffman tree because it isn't a full tree");
        }
        if (internalNodesContainValues(ROOT_NODE)) {
            Assertions.fail("ERROR: The tree is not a huffman tree because at least one of its internal nodes stores " +
                    "information (contains a character other than the null character).");
        }
        if (!leafNodesContainValues(ROOT_NODE)) {
            Assertions.fail("ERROR: The tree is not a huffman tree because at least one of its leaf nodes doesn't " +
                    "store information (contains a null character).");
        }
        if (!isSumTree(ROOT_NODE)) {
            Assertions.fail("ERROR: The tree is not a huffman tree it isn't a sum tree.");
        }
    }

}
