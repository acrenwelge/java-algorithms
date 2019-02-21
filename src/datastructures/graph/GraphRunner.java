package datastructures.graph;

public class GraphRunner {

	public static void main(String[] args) {
		Graph g = new Graph();
		g.addNode(new Node(1));
		g.addNode(new Node(2));
		g.addNode(new Node(3));
		g.addEdge(1, 2);
		g.printGraph();
	}

}
