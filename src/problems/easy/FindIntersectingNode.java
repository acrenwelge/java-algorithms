package problems.easy;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * This problem was asked by Google.

Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.

For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.

In this example, assume nodes with the same value are the exact same node objects.

Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.
 * @author Andrew
 *
 */
public class FindIntersectingNode {
	List<Node> list1;
	List<Node> list2;
	
	class Node {
		int val;
		
		public Node(int v) {
			this.val = v;
		}
		
		public boolean equals(Node n) {
			return this.val == n.val;
		}
	}
	
	public Node findIntersection() {
		for (int i=0; i<list1.size(); i++) {
			Node current = list1.get(i);
			for (int j=0; j<list2.size(); j++) {
				if (list2.get(j).equals(current)) return current;
			}
		}
		return null;
	}
	
	@Test
	public void defaultTest() {
		list1 = new LinkedList<>();
		list2 = new LinkedList<>();
		list1.add(new Node(3));
		list1.add(new Node(7));
		list1.add(new Node(8));
		list1.add(new Node(10));
		list2.add(new Node(99));
		list2.add(new Node(1));
		list2.add(new Node(8));
		list2.add(new Node(10));
		assertEquals(8, findIntersection().val);
	}
	
}
