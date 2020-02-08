package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Given a binary tree, return all paths from the root to leaves.

For example, given the tree:

   1
  / \
 2   3
    / \
   4   5
Return [[1, 2], [1, 3, 4], [1, 3, 5]].
 * @author Andrew
 */
public class BinaryTreeAllPaths {
	
	static class Node {
		int val;
		Node left;
		Node right;
		public Node(int v, Node left, Node right) {
			this.val = v;
			this.left = left;
			this.right = right;
		}
	}
	
	/*
	 * Approach:
	 * - breadth-first search
	 */
	public static List<List<Integer>> getAllPathsRootToLeaves(Node tree) {
		List<Integer> rootPath = new ArrayList<>();
		rootPath.add(tree.val);
		return getPathsToLeavesFromNode(tree, rootPath);
	}
	
	private static List<List<Integer>> getPathsToLeavesFromNode(Node node, List<Integer> pathToCurrent) {
		Node current = node;
		List<List<Integer>> allPaths = new ArrayList<>();
		if (current.left == null && current.right == null) {
			allPaths.add(pathToCurrent);
			return allPaths;
		} else if (current.left == null) {
			pathToCurrent.add(current.right.val);
			return getPathsToLeavesFromNode(current.right, pathToCurrent);
		} else if (current.right == null) {
			pathToCurrent.add(current.left.val);
			return getPathsToLeavesFromNode(current.left, pathToCurrent);
		} else {
			List<Integer> toLeft = new ArrayList<>();
			pathToCurrent.forEach(p -> toLeft.add(p));
			toLeft.add(current.left.val);
			List<List<Integer>> pathsLeft = getPathsToLeavesFromNode(current.left, toLeft);
			pathToCurrent.add(current.right.val);
			List<List<Integer>> pathsRight = getPathsToLeavesFromNode(current.right, pathToCurrent);
			allPaths.addAll(pathsLeft);
			allPaths.addAll(pathsRight);
			return allPaths;
		}
	}
	
	@Test
	public void defaultTest() {
		Node root = new Node(1,
				new Node(2,null,null),
				new Node(3, 
						new Node(4,null,null), 
						new Node(5,null,null)));
		List<List<Integer>> p = getAllPathsRootToLeaves(root);
		List<Integer> path1 = Arrays.asList(1,2);
		List<Integer> path2 = Arrays.asList(1,3,4);
		List<Integer> path3 = Arrays.asList(1,3,5);
		assertEquals(Arrays.asList(path1,path2,path3), p);
	}
}
