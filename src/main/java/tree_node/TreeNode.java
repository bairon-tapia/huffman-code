package tree_node;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public final class TreeNode implements AbstractNode<Character> {

    private static final int DEFAULT_FREQUENCY;
    private static final String DEFAULT_ROUTE;

    static {
        DEFAULT_FREQUENCY = 1;
        DEFAULT_ROUTE = "";
    }

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Character element;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private TreeNode left;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private TreeNode right;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int frequency;
    @Getter
    @Setter
    private String route;

    public TreeNode(@NonNull final char element) {
        setElement(element);
        setLeft(null);
        setRight(null);
        setFrequency(DEFAULT_FREQUENCY);
        setRoute(DEFAULT_ROUTE);
    }

    public TreeNode(@NonNull final char element, @NonNull final TreeNode left, @NonNull final TreeNode right,
                    final int frequency) {
        setElement(element);
        setLeft(left);
        setRight(right);
        setFrequency(frequency);
        setRoute(DEFAULT_ROUTE);
    }

    @Override
    public int hashCode() {
        return (Objects.hash(element, frequency));
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
        return (String.format("%1c - %-6d - %-30s%n", element, frequency, route));
    }

    public boolean isLeaf() {
        return ((left == null) && (right == null));
    }

    public void updateFrequency() {
        frequency++;
    }

}
