package codify_operations;

import display_operations.DisplayHelper;
import lombok.NonNull;
import route_operations.RouteHelper;
import string_operations.ByteToBitStringParser;
import string_operations.StringJoiner;
import tree_node.TreeNode;

import java.util.List;

public final class Decoder {

    public Decoder(@NonNull final TreeNode<Character> rootNode, final int lastByteLength) {
        final byte[] decodedBytes = FilesHandler.decode();
        DisplayHelper.displayBytes(decodedBytes);
        final List<String> decodedBitStrings = ByteToBitStringParser.toBitStrings(decodedBytes, lastByteLength);
        DisplayHelper.displayBitStrings(decodedBitStrings);
        final String decodedRoute = StringJoiner.join(decodedBitStrings);
        DisplayHelper.displayRoute(decodedRoute);
        final String string = RouteHelper.decodeRoute(rootNode, decodedRoute);
        DisplayHelper.displayString(string);
        FilesHandler.writeString(string);
    }

}
