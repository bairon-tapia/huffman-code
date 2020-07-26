package codify_operations;

import java.util.List;

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
    private TreeNode treeNode;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int lastByteLength;

    public Encoder() {
        final String string = FilesHandler.readString();
        DisplayHelper.displayString(string);
        final var mapCharAsKey = Mapping.createMapCharAsKey(string);
        DisplayHelper.displayMap(mapCharAsKey);
        final var priorityQueue = Mapping.createPriorityQueue(mapCharAsKey);
        final TreeNode rootNode = Mapping.createHuffmanTree(priorityQueue);
        DisplayHelper.displayHuffmanTree(rootNode);
        final var sortedMapCharAsKey = Mapping.createSortedMapCharAsKey(mapCharAsKey);
        DisplayHelper.displayMapWithRoutes(sortedMapCharAsKey);
        final var mapRouteAsKey = Mapping.createMapRouteAsKey(mapCharAsKey);
        final var filteredMap = Mapping.createMapFilteredByRoutes(mapRouteAsKey);
        DisplayHelper.displayFilteredMap(filteredMap);
        final String encodedRoute = RouteHelper.encodeRoute(mapCharAsKey, string);
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
