package tree_node;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public final class TreeNode<T extends Comparable<T>> {

    private static final int DEFAULT_FREQUENCY;
    private static final String DEFAULT_ROUTE;

    static {
        DEFAULT_FREQUENCY = 1;
        DEFAULT_ROUTE = "";
    }

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private T element;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private TreeNode<T> left;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private TreeNode<T> right;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int frequency;
    @Getter
    @Setter
    private String route;

    public TreeNode(@NonNull T element) {
        setElement(element);
        setLeft(null);
        setRight(null);
        setFrequency(DEFAULT_FREQUENCY);
        setRoute(DEFAULT_ROUTE);
    }

    public TreeNode(@NonNull final T element, @NonNull final TreeNode<T> left, @NonNull final TreeNode<T> right,
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

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return (true);
        }
        if (!(object instanceof TreeNode)) {
            return (false);
        }
        final TreeNode<T> treeNode = (TreeNode<T>) object;
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
