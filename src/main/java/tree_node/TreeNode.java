package tree_node;

import java.util.Objects;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public final class TreeNode implements AbstractNode<Character>, Comparable<TreeNode> {

    private static final int DEFAULT_FREQUENCY;
    private static final String DEFAULT_ROUTE;

    static {
        DEFAULT_FREQUENCY = 1;
        DEFAULT_ROUTE = "";
    }

    @Getter
    @Setter
    private Character element;
    @Getter
    @Setter
    private TreeNode left;
    @Getter
    @Setter
    private TreeNode right;
    @Getter
    @Setter
    private int frequency;
    @Getter
    @Setter
    private String route;

    public TreeNode(final char element) {
        setElement(element);
        setLeft(null);
        setRight(null);
        setFrequency(DEFAULT_FREQUENCY);
        setRoute(DEFAULT_ROUTE);
    }

    public TreeNode(final char element, @NonNull final TreeNode left, @NonNull final TreeNode right,
                    final int frequency) {
        setElement(element);
        setLeft(left);
        setRight(right);
        setFrequency(frequency);
        setRoute(DEFAULT_ROUTE);
    }

    public static void buildRoutes(@NonNull final String route, final TreeNode treeNode) {
        if (treeNode.isLeaf()) {
            treeNode.setRoute(route);
            return;
        }
        buildRoutes(route + "0", treeNode.getLeft());
        buildRoutes(route + "1", treeNode.getRight());
    }

    public static String traversal(@NonNull final TreeNode root) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.toString());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(stringBuilder, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(stringBuilder, "", pointerRight, root.getRight(), false);

        return (stringBuilder.toString());
    }

    private static void traverseNodes(@NonNull final StringBuilder stringBuilder, @NonNull final String padding,
                                      @NonNull final String pointer, final TreeNode treeNode, boolean hasRightSibling) {
        if (treeNode == null) {
            return;
        }
        stringBuilder.append("\n");
        stringBuilder.append(padding);
        stringBuilder.append(pointer);
        stringBuilder.append(treeNode.toString());
        StringBuilder paddingBuilder = new StringBuilder(padding);

        if (hasRightSibling) {
            paddingBuilder.append("│  ");
        } else {
            paddingBuilder.append("   ");
        }

        String paddingForBoth = paddingBuilder.toString();
        String pointerRight = "└──";
        String pointerLeft = (treeNode.getRight() != null) ? "├──" : "└──";

        traverseNodes(stringBuilder, paddingForBoth, pointerLeft, treeNode.getLeft(), treeNode.getRight() != null);
        traverseNodes(stringBuilder, paddingForBoth, pointerRight, treeNode.getRight(), false);
    }

    @Override
    public int compareTo(final TreeNode treeNode) {
        final int leftFrequency = this.frequency;
        final int rightFrequency = treeNode.frequency;
        if (leftFrequency != rightFrequency) {
            return (Integer.compare(leftFrequency, rightFrequency));
        }
        final int leftRouteLength = this.route.length();
        final int rightRouteLength = treeNode.route.length();
        if (leftRouteLength != rightRouteLength) {
            return (Integer.compare(leftRouteLength, rightRouteLength));
        }
        final String leftRoute = this.route;
        final String rightRoute = treeNode.route;
        return (leftRoute.compareTo(rightRoute));
    }

    @Override
    public int hashCode() {
        return (Objects.hash(element, frequency, route));
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return (true);
        }
        if (!(object instanceof TreeNode)) {
            return (false);
        }
        final TreeNode treeNode = (TreeNode) object;
        return (Objects.equals(element, treeNode.getElement()) && Objects.equals(frequency, treeNode.getFrequency()) && Objects.equals(route, treeNode.getRoute()));
    }

    @Override
    public String toString() {
        return (route.isEmpty() ? String.format("%1c - %-6d", element, frequency) : String.format("%1c - %-6d - " +
                "%-30s", element, frequency, route));
    }

    public boolean isLeaf() {
        return ((left == null) && (right == null));
    }

    public void updateFrequency() {
        frequency++;
    }

}
