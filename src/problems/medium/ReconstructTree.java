package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

/**
 * Given pre-order and in-order traversals of a binary tree, write a function to reconstruct the tree.

For example, given the following preorder traversal:

[a, b, d, e, c, f, g]

And the following inorder traversal:

[d, b, e, a, f, c, g]

You should return the following tree:

    a
   / \
  b   c
 / \ / \
d  e f  g
 * @author Andrew
 *
 */
public class ReconstructTree {
	
	static class Tree<T> {
		T val;
		Tree<T> left;
		Tree<T> right;
		
		public Tree(T val, Tree<T> left, Tree<T> right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
		public Tree(T val) {
			this.val = val;
		}
		
		public void setLeft(Tree<T> left) {
			this.left = left;
		}
		
		public void setRight(Tree<T> right) {
			this.right = right;
		}
	}
	
	/* Approach:
	 * - go through preorder array until it matches the first element of inorder (this means we are at leftmost (ex: 'd' above))
	 * - look at next element of inorder array
	 *   - if it matches the most recent element in preorder this branch of tree is done and we go back up a level
	 *   - if it doesn't match, then create a new branch to the right of the current element
	 * - continue with preorder array
	 */
	public static <T> Tree<T> reconstruct(T[] preorder, T[] inorder) {
		if (preorder.length == 0) return null;
		Iterator<T> preIt = Arrays.asList(preorder).iterator();
		Iterator<T> inIt = Arrays.asList(inorder).iterator();
		while(preIt.hasNext()) {
			T current = preIt.next();
			if (current.equals(inIt.next())) {
				
			}
		}
		for (int i=0; i<preorder.length; i++) {
			T current = preorder[i];
			if (current == inorder[0]) {
				
			}
		}
		return new Tree<>(preorder[0]);
	}
	
	@Test
	public void defaultTest() {
		Character[] preorder = {'a','b','d','e','c','f','g'};
		Character[] inorder = {'d','b','e','a','f','c','g'};
		Tree<Character> tree = new Tree<Character>('a',new Tree<Character>('b',new Tree<Character>('d'),new Tree<>('e'))
				, new Tree<>('c',new Tree<>('f'),new Tree<>('g')));
		assertEquals(tree, reconstruct(preorder, inorder));
	}
}
