package datastructures.graph;

public abstract class AbstractNode<T> {
	
	T value;
	
	public AbstractNode(T value) {
		this.value = value;
	}
	
	public AbstractNode() {}
	
	T getValue() {
		return value;
	}
}
