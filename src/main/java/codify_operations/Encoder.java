package codify_operations;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import display_operations.DisplayHelper;
import mapping_operations.Mapping;
import route_operations.RouteHelper;
import string_operations.BitStringToByteParser;
import string_operations.StringSplitter;
import tree_node.TreeNode;

public final class Encoder {

    private static final int BYTE_LENGTH = 8;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private TreeNode<Character> treeNode;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int lastByteLength;

    public Encoder() {
        final String string = FilesHandler.readString();
        DisplayHelper.displayString(string);
        final Map<Character, TreeNode<Character>> charAsKey = Mapping.createMapCharacterAsKey(string);
        DisplayHelper.displayMap(charAsKey);
        final PriorityQueue<TreeNode<Character>> priorityQueue = Mapping.createPriorityQueue(charAsKey);
        final TreeNode<Character> rootNode = Mapping.createHuffmanTree(priorityQueue);
        DisplayHelper.displayMapWithRoutes(charAsKey);
        final Map<String, TreeNode<Character>> routeAsKey = Mapping.createMapRouteAsKey(charAsKey);
        final Map<String, TreeNode<Character>> filteredMap = Mapping.filterByRoutesLength(routeAsKey);
        DisplayHelper.displayFilteredMap(filteredMap);
        final String encodedRoute = RouteHelper.encodeRoute(charAsKey, string);
        DisplayHelper.displayRoute(encodedRoute);
        final List<String> encodedBitStrings = StringSplitter.split(encodedRoute, BYTE_LENGTH);
        DisplayHelper.displayBitStrings(encodedBitStrings);
        final int lastBitStringLength = StringSplitter.getLastStringLength(encodedBitStrings);
        final byte[] encodedBytes = BitStringToByteParser.toBytes(encodedBitStrings);
        DisplayHelper.displayBytes(encodedBytes);
        FilesHandler.encode(encodedBytes);
        setTreeNode(rootNode);
        setLastByteLength(lastBitStringLength);
    }

}
