package problems.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This problem was asked by Google.

A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.

Given the root to a binary tree, count the number of unival subtrees.

For example, the following tree has 5 unival subtrees:
 * @author Andrew
 */
public class Eight {
	
	static class Node {
		int val;
		Node left;
		Node right;
		
		public Node(int val, Node l, Node r) {
			this.val = val;
			this.left = l;
			this.right = r;
		};
	}
	
	public int countUnivalSubtrees(Node node) {
		if (node.left == null && node.right == null) {
			return 1;
		} else if (node.left == null) {
			return countUnivalSubtrees(node.right);
		} else if (node.right == null) {
			return countUnivalSubtrees(node.left);
		}
		int numLeft = countUnivalSubtrees(node.left);
		int numRight = countUnivalSubtrees(node.right);
		if (node.left.equals(node.right)) {
			return 1 + numLeft + numRight;
		} else {
			return numLeft + numRight;
		}
	}
	
	@Test
	public void trivialTest() {
		assertEquals(1, countUnivalSubtrees(new Node(1, null, null)));
	}
	
	@Test
	public void defaultTest() {
		Node oneLeaf = new Node(1, null, null);
		Node zeroLeaf = new Node(0, null, null);
		Node right = new Node(0, new Node(1, oneLeaf, oneLeaf), zeroLeaf);
		Node root = new Node(0, oneLeaf, right);
		assertEquals(5, countUnivalSubtrees(root));
	}
	
	@Test
	public void customTest() {
		Node oneLeaf = new Node(1, null, null);
		Node zeroLeaf = new Node(0, null, null);
		Node two = new Node(0, oneLeaf, zeroLeaf);
		Node three = new Node(0, zeroLeaf, zeroLeaf);
		assertEquals(2, countUnivalSubtrees(two));
		assertEquals(3, countUnivalSubtrees(three));
	}

}
