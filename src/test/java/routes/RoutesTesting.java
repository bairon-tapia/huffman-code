package routes;

import mapping_operations.Mapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import route_operations.RouteHelper;
import tree_node.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

class RoutesTesting {

    private static final String DEFAULT_STRING = "This is a test!";
    private static final Map<Character, TreeNode<Character>> CHAR_AS_KEY =
            Mapping.createMapCharacterAsKey(DEFAULT_STRING);
    private static final PriorityQueue<TreeNode<Character>> PRIORITY_QUEUE = Mapping.createPriorityQueue(CHAR_AS_KEY);
    private static final TreeNode<Character> ROOT_NODE = Mapping.createHuffmanTree(PRIORITY_QUEUE);
    private static final Map<String, TreeNode<Character>> ROUTE_AS_KEY = Mapping.createMapRouteAsKey(CHAR_AS_KEY);
    private static final String ENCODED_ROUTE = RouteHelper.encodeRoute(CHAR_AS_KEY, DEFAULT_STRING);

    @Test
    void areRoutesUnique() {
        final Set<String> routes = new HashSet<>();
        for (Map.Entry<Character, TreeNode<Character>> entry : CHAR_AS_KEY.entrySet()) {
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
        if (CHAR_AS_KEY.size() != ROUTE_AS_KEY.size()) {
            Assertions.fail("Error: The maps do not have the same amount of elements, thus it is not possible to " +
                    "assess whether they contain the same elements");
        }
        final Map<Character, TreeNode<Character>> sortedCharAsKey = CHAR_AS_KEY
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getRoute()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        final Map<String, TreeNode<Character>> sortedRouteAsKey = new TreeMap<>(ROUTE_AS_KEY);
        final Iterator<Map.Entry<Character, TreeNode<Character>>> iteratorCharAsKey =
                sortedCharAsKey.entrySet().iterator();
        final Iterator<Map.Entry<String, TreeNode<Character>>> iteratorRouteAsKey =
                sortedRouteAsKey.entrySet().iterator();
        while ((iteratorCharAsKey.hasNext()) || (iteratorRouteAsKey.hasNext())) {
            final Map.Entry<Character, TreeNode<Character>> entryCharAsKey = iteratorCharAsKey.next();
            final Map.Entry<String, TreeNode<Character>> entryRouteAsKey = iteratorRouteAsKey.next();
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
        TreeNode<Character> auxiliaryNode = ROOT_NODE;
        final int n = ENCODED_ROUTE.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            final char bitCharacter = ENCODED_ROUTE.charAt(i);
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
                    auxiliaryNode = ROOT_NODE;
                    count++;
                }
            }

        }
    }

}
