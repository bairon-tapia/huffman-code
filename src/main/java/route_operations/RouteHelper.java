package route_operations;

import lombok.NonNull;
import tree_node.TreeNode;

import java.util.Map;

public final class RouteHelper {

    private RouteHelper() {
        throw new UnsupportedOperationException();
    }

    public static void buildRoutes(@NonNull final TreeNode<Character> treeNode, @NonNull final String route) {
        if (treeNode.isLeaf()) {
            treeNode.setRoute(route);
            return;
        }
        buildRoutes(treeNode.getLeft(), route + "0");
        buildRoutes(treeNode.getRight(), route + "1");
    }

    public static String encodeRoute(@NonNull final Map<Character, TreeNode<Character>> map,
                                     @NonNull final String fullString) {
        final StringBuilder completeRoute = new StringBuilder();
        final int n = fullString.length();
        for (int i = 0; i < n; i++) {
            final char character = fullString.charAt(i);
            final String partialRoute = map.get(character).getRoute();
            completeRoute.append(partialRoute);
        }
        return (completeRoute.toString());
    }

    public static String decodeRoute(@NonNull final TreeNode<Character> rootNode, @NonNull final String route) {
        TreeNode<Character> auxiliaryNode = rootNode;
        final StringBuilder stringBuilder = new StringBuilder();
        final int n = route.length();
        for (int i = 0; i < n; i++) {
            final char bitCharacter = route.charAt(i);
            if (bitCharacter == '0') {
                auxiliaryNode = auxiliaryNode.getLeft();
            }
            if (bitCharacter == '1') {
                auxiliaryNode = auxiliaryNode.getRight();
            }
            if (auxiliaryNode.isLeaf()) {
                final char character = auxiliaryNode.getElement();
                stringBuilder.append(character);
                auxiliaryNode = rootNode;
            }

        }
        return (stringBuilder.toString());
    }

}
