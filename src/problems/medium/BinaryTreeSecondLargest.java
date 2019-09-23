package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Given the root to a binary search tree, find the second largest node in the tree.
 * @author Andrew
 *
 */
public class BinaryTreeSecondLargest {
	
	static List<Node> values = new ArrayList<>();
	
	class Node implements Comparable<Node>{
		Node left;
		Node right;
		int val;
		public Node(Node l, Node r, int v) {
			this.left = l;
			this.right = r;
			this.val = v;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.val > o.val ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return "ID:" + super.hashCode() + "; VAL:" + val;
		}
		
		@Override
		public boolean equals(Object obj) {
			Node other = (Node) obj;
			if (other == null) return false;
			else if (this.val == other.val) {
				if (this.left == null && this.right == null ) {
					return other.left == null && other.right == null;
				} else if (this.left != null && this.right != null) {
					return this.left.equals(other.left) && this.right.equals(other.right);
				} else if (this.left != null) {
					return this.left.equals(other.left) && other.right == null;
				} else {
					return this.right.equals(other.right) && other.left == null;
				}
			} else {
				return false;
			}
		}
		
	}
	
	public Node getSecondLargest(Node root) {
		if (root != null) values.add(root);
		if (root.left == null && root.right == null) {
			return null;
		} else if (root.left == null) {
			recurse(root.right);
		} else if (root.right == null) {
			recurse(root.left);
		} else {
			recurse(root.left);
			recurse(root.right);
		}
		Collections.sort(values);
		return values.get(values.size()-2);
	}
	
	private void recurse(Node n) {
		values.add(n);
		if (n.left == null && n.right == null) {
		} else if (n.left == null) {
			recurse(n.right);
		} else if (n.right == null) {
			recurse(n.left);
		} else {
			recurse(n.left);
			recurse(n.right);
		}
	}
	
	@Test
	public void myTest() {
		Node right = new Node(null,null,5);
		Node left = new Node(new Node(null,null,6),null,3);
		Node root = new Node(left,right,1);
		assertEquals(right, getSecondLargest(root));
	}
	
	@Test
	public void myTest2() {
		Node right = new Node(null,new Node(null,null, 7),5);
		Node left = new Node(new Node(new Node(null,null,10),null,6),null,3);
		Node root = new Node(left,right,9);
		assertEquals(root, getSecondLargest(root));
	}

}
