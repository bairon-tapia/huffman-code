package main;

import codify_operations.Decoder;
import codify_operations.Encoder;
import codify_operations.FilesHandler;
import tree_node.TreeNode;

public final class Main {

    public static void main(final String[] args) {
        FilesHandler.displayIntroduction();
        FilesHandler.setUpFiles();
        final Encoder encoder = new Encoder();
        final TreeNode rootNode = encoder.getTreeNode();
        final int lastByteLength = encoder.getLastByteLength();
        new Decoder(rootNode, lastByteLength);
        FilesHandler.displayFileSizes();
    }

}
