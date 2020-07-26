package routes;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mapping_operations.Mapping;
import route_operations.RouteHelper;
import tree_node.TreeNode;

class RoutesTesting {

    private static final String DEFAULT_STRING = "This is a test!";

    private final Map<Character, TreeNode> mapCharAsKey = Mapping.createMapCharAsKey(DEFAULT_STRING);
    private final PriorityQueue<TreeNode> priorityQueue = Mapping.createPriorityQueue(mapCharAsKey);
    private final TreeNode rootNode = Mapping.createHuffmanTree(priorityQueue);
    private final Map<String, TreeNode> mapRouteAsKey = Mapping.createMapRouteAsKey(mapCharAsKey);
    private final String encodedRoute = RouteHelper.encodeRoute(mapCharAsKey, DEFAULT_STRING);

    @Test
    void areRoutesUnique() {
        final Set<String> routes = new HashSet<>();
        for (var entry : mapCharAsKey.entrySet()) {
            final char character = entry.getKey();
            final String route = entry.getValue().getRoute();
            if (routes.contains(route)) {
                Assertions.fail("Error: Attempted to add the route " + route + " associated with the character " + character +
                        ", but it was previously associated to another character, thus it is not unique");
            } else {
                routes.add(route);
            }
        }
    }

    @Test
    void areRoutesEqual() {
        if (mapCharAsKey.size() != mapRouteAsKey.size()) {
            Assertions.fail("Error: The maps do not have the same amount of elements, thus it is not possible to " +
                    "assess whether they contain the same elements");
        }
        final var sortedCharAsKey = Mapping.createMapSortedByRoute(mapCharAsKey);
        final var sortedRouteAsKey = new TreeMap<>(mapRouteAsKey);
        final var iteratorCharAsKey = sortedCharAsKey.entrySet().iterator();
        final var iteratorRouteAsKey = sortedRouteAsKey.entrySet().iterator();
        while ((iteratorCharAsKey.hasNext()) || (iteratorRouteAsKey.hasNext())) {
            final var entryCharAsKey = iteratorCharAsKey.next();
            final var entryRouteAsKey = iteratorRouteAsKey.next();
            final char charOne = entryCharAsKey.getKey();
            final char charTwo = entryRouteAsKey.getValue().getElement();
            final String routeOne = entryCharAsKey.getValue().getRoute();
            final String routeTwo = entryRouteAsKey.getKey();
            if ((charOne != charTwo) || !(routeOne.equals(routeTwo))) {
                Assertions.fail("Error: The route " + routeOne + " associated with the character " + charOne + " in " +
                        "map one is not associated with the same character " + charTwo + " in map two");
            }
        }
    }

    @Test
    void areRoutesValid() {
        TreeNode auxiliaryNode = rootNode;
        final int n = encodedRoute.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            final char bitCharacter = encodedRoute.charAt(i);
            if (bitCharacter == '0') {
                if (auxiliaryNode.getLeft() == null) {
                    Assertions.fail("Error: The encoded route indicates that the node: " + auxiliaryNode + " has a " +
                            "child to its left, even though there is no such element");
                } else {
                    auxiliaryNode = auxiliaryNode.getLeft();
                }
            }
            if (bitCharacter == '1') {
                if (auxiliaryNode.getRight() == null) {
                    Assertions.fail("Error: The encoded route indicates that the node: " + auxiliaryNode + " has a " +
                            "child to its right, even though there is no such element");
                } else {
                    auxiliaryNode = auxiliaryNode.getRight();
                }
            }
            if (auxiliaryNode.isLeaf()) {
                final char character = auxiliaryNode.getElement();
                final char comparisonCharacter = DEFAULT_STRING.charAt(count);
                if (character != comparisonCharacter) {
                    Assertions.fail("Error: Even though the route " + auxiliaryNode.getRoute() + " is valid, it " +
                            "should be associated to the character " + comparisonCharacter + ", and not to the " +
                            "character " + auxiliaryNode.getElement() + " as the encoded route indicates");
                } else {
                    auxiliaryNode = rootNode;
                    count++;
                }
            }

        }
    }

}
