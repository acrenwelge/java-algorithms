package datastructures.graph;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
        static final Logger log = LogManager.getRootLogger();

	Map<Node, List<Node>> adjNodes = new HashMap<>();

	public Graph() {}

	public Graph(int numNodes) {
		for (int i=0; i<numNodes; i++) {
			adjNodes.put(new Node(), new LinkedList<Node>());
		}
	}

	List<Node> getEdgesOfNode(Node n) {
		return adjNodes.get(n);
	}

	void addNode(Node n) {
		adjNodes.putIfAbsent(n, new LinkedList<Node>());
	}

	void removeNode(Node n) {
		adjNodes.remove(n);
	}

	void addEdge(int source, int dest) {
		Node a = new Node(source);
		Node b = new Node(dest);
		if (adjNodes.containsKey(a) && adjNodes.containsKey(b)) {
			adjNodes.get(a).add(b);
			adjNodes.get(b).add(a);
		}
	}

	void removeEdge(int source, int dest) {
		Node n1 = new Node(source);
		Node n2 = new Node(dest);
		List<Node> edgeN1 = adjNodes.get(n1);
		List<Node> edgeN2 = adjNodes.get(n2);
		if (edgeN1 != null) edgeN1.remove(n2);
		if (edgeN2 != null) edgeN2.remove(n1);
	}

	void printGraph() {
		for (Map.Entry<Node, List<Node>> ln : adjNodes.entrySet()) {
			StringBuilder sb = new StringBuilder("edges of node " + ln.getKey().getValue() + ": ");
			for (Node innerNode : ln.getValue()) {
				sb.append(innerNode.getValue() + ", ");
			}
			log.debug(sb);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjNodes == null) ? 0 : adjNodes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (adjNodes == null) {
			if (other.adjNodes != null)
				return false;
		} else if (!adjNodes.equals(other.adjNodes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Graph [adjNodes=" + adjNodes + "]";
	}

}
