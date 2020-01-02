package problems.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Suppose an arithmetic expression is given as a binary tree. Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.

Given the root to such a tree, write a function to evaluate it.

For example, given the following tree:

    *
   / \
  +    +
 / \  / \
3  2  4  5
You should return 45, as it is (3 + 2) * (4 + 5).
 * @author Andrew
 *
 */
public class MathTree {
	MathTree left;
	MathTree right;
	char op;
	int val;
	
	static MathTree makeNode(MathTree left, MathTree right, char op) {
		MathTree mt = new MathTree();
		mt.left = left;
		mt.right = right;
		mt.op = op;
		return mt;
	}
	
	static MathTree makeLeaf(int v) {
		MathTree mt = new MathTree();
		mt.val = v;
		return mt;
	}
	
	static double evaluate(MathTree mt) {
		if (mt.left == null && mt.right == null) { // it's a leaf
			return mt.val;
		} else {
			MathTree left = mt.left;
			MathTree right = mt.right;
			double l = evaluate(left);
			double r = evaluate(right);
			switch(mt.op) {
			case '+': return l + r;
			case '-': return l - r;
			case '*': return l * r;
			case '/': return l / r;
			default: throw new RuntimeException("invalid operator reached");
			}
		}
	}

	@Test
	public void mathTreeDefaultTest() {
		MathTree mt = MathTree.makeNode(
				MathTree.makeNode(
						MathTree.makeLeaf(3),
						MathTree.makeLeaf(2),
						'+'
						),
				MathTree.makeNode(
						MathTree.makeLeaf(4),
						MathTree.makeLeaf(5),
						'+'
						),
				'*'
				);
		assertEquals(45, evaluate(mt), 0.01);
	}
	
	@Test
	public void mathTreeCustomTest() {
		MathTree mt = MathTree.makeNode(
				MathTree.makeNode(
						MathTree.makeNode(
								MathTree.makeLeaf(-1),
								MathTree.makeLeaf(7),
								'+'),
						MathTree.makeLeaf(2),
						'/'
						),
				MathTree.makeNode(
						MathTree.makeLeaf(5),
						MathTree.makeLeaf(1),
						'-'
						),
				'*'
				);
		assertEquals(12, evaluate(mt), 0.01);
	}
}
