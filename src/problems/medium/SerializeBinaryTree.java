package problems.medium;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

/**
 * Good morning! Here's your coding interview problem for today.

This problem was asked by Google.

Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
 * @author Andrew
 * Solution algorithm: depth-first (NLR)
 */
public class SerializeBinaryTree {
        static final Logger log = LogManager.getRootLogger();

	static final String delim = ",";
	static final String nullVal = "null";

	static class Node {

		String value;
		Node left;
		Node right;

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Node)) {
				return false;
			}
			Node n = (Node) obj;
			if (this.value.equals(n.value)) {
				if (this.left == null && this.right == null && n.left == null && n.right == null)
				  return true;
				else if (this.left != null && n.left != null && this.right != null && n.right != null)
				  return this.left.equals(n.left) && this.right.equals(n.right);
				else if (this.left == null && n.left == null && this.right != null && n.right != null)
				  return this.right.equals(n.right);
				else if (this.left != null && n.left != null && this.right == null && n.right == null)
				  return this.left.equals(n.left);
				else return false;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			return serialize(this, new StringBuilder());
		}

		public Node(String val, Node left, Node right) {
			this.value = val;
			this.left = left;
			this.right = right;
		}

		/**
		 * Prints in this format recursively:
		 *   value[delim]left-val[delim]right-val[delim] (recurse w/ left node) (recurse w/ right node)
		 * @return
		 */
		public static String serialize(Node node, StringBuilder sb) {
			if (node == null) {
				sb.append(nullVal);
			} else {
				sb.append(node.value);
					sb.append(delim);
					serialize(node.left, sb);
					sb.append(delim);
					serialize(node.right, sb);
			}
			return sb.toString();
		}

		public static Node deserialize(String str) {
			if (str == null || str.length() == 0) return null;
			Queue<String> nodeQ = new LinkedList<>();
			nodeQ.addAll(Arrays.asList(str.split(delim)));
		    return deserializeRecurse(nodeQ);
		}

		public static Node deserializeRecurse(Queue<String> q) {
			String val = q.poll();
		    if (val.equals(nullVal)) {
		      return null;
		    }
		    Node left = deserializeRecurse(q);
		    Node right = deserializeRecurse(q);
		    return new Node(val, left, right);
		}

	}

	Node root;

	@Before
	public void setup() {
		Node left = new Node("left",new Node("left.left",null,null),null);
		Node right = new Node("right",null,null);
		root = new Node("root",left,right);
	}

	@Test
	public void testSerialization() {
		log.debug(Node.serialize(root, new StringBuilder()));
	}

	@Test
	public void sanity() {
		assertEquals(root, root);
	}

	@Test
	public void testBoth() {
		assertEquals(root, Node.deserialize(Node.serialize(root, new StringBuilder())));
	}
}
