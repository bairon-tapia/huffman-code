package trees;

import java.util.Map;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mapping_operations.Mapping;
import tree_node.TreeNode;

class TreeTesting {

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

    @Test
    void isValidHuffmanTree() {
        if (!isFullTree(ROOT_NODE)) {
            Assertions.fail("ERROR: The tree is not a huffman tree because it isn't a full tree");
        }
    }

}
