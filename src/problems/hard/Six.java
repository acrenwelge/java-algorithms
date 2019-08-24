package problems.hard;

/**
 * An XOR linked list is a more memory efficient doubly linked list. 
 * Instead of each node holding next and prev fields, it holds a field named both, 
 * which is an XOR of the next node and the previous node. 
 * Implement an XOR linked list; it has an add(element) which adds the element to the end, 
 * and a get(index) which returns the node at index.
 * @author Andrew
 *
 */
public class Six {
	static class Node {
		int both;
		Node(int b) {
			this.both = b;
		}
	}
	
	static class XorList {
		Node first = null;
		
		public void add(Node n) {
			if (first == null) first = n;
			int addr = 0;
			Node current = first;
			do {
				addr = addr ^ current.both;
				if (addr == 0) break;
				current = reference(addr);
			} while (addr != 0);
		}
		
		public Node get(int index) {
			return new Node(0);
		}
		
		public static int dereference(Node n) {
			return 0;
		}
		public static Node reference(int addr) {
			return new Node(0);
		}
	}
}
