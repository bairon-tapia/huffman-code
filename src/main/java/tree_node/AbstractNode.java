package tree_node;

public interface AbstractNode<T extends Comparable<T>> {

    T getElement();

    AbstractNode<T> getLeft();

    AbstractNode<T> getRight();

}
