package problems.medium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;

import org.junit.Test;

/**
 * Write a map implementation with a get function that lets you retrieve the value of a key at a particular time.

It should contain the following methods:

set(key, value, time): sets key to value for t = time.
get(key, time): gets the key at t = time.
The map should work like this. If we set a key at a particular time, it will maintain 
that value forever or until it gets set at a later time. In other words, when we get a 
key at a time, it should return the value that was set for that key set at the most recent time.

Consider the following examples:

d.set(1, 1, 0) # set key 1 to value 1 at time 0
d.set(1, 2, 2) # set key 1 to value 2 at time 2
d.get(1, 1) # get key 1 at time 1 should be 1
d.get(1, 3) # get key 1 at time 3 should be 2
d.set(1, 1, 5) # set key 1 to value 1 at time 5
d.get(1, 0) # get key 1 at time 0 should be null // don't agree here
d.get(1, 10) # get key 1 at time 10 should be 1
d.set(1, 1, 0) # set key 1 to value 1 at time 0
d.set(1, 2, 0) # set key 1 to value 2 at time 0
d.get(1, 0) # get key 1 at time 0 should be 2
 * @author Andrew
 */
public class TimeMap<K,V> {
	Node<K,V> firstNode;
	
	static class Node<K,V> {
		K key;
		LinkedList<V> values;
		LinkedList<Integer> times;
		Node<K,V> next;
		public Node(K key, V val, int time) {
			this.key = key;
			this.values = new LinkedList<>();
			values.add(val);
			this.times = new LinkedList<>();
			times.add(time);
		}
	}
	
	public void set(K key, V val, int time) {
		Node<K,V> currentNode = firstNode;
		Node<K,V> prevNode = firstNode;
		boolean foundNode = false;
		while (currentNode != null) {
			if (currentNode.key == key) {
				foundNode = true;
				boolean foundTime = false;
				for (int i=0; i < currentNode.times.size(); i++) {
					if (currentNode.times.get(i) == time) {
						foundTime = true;
						currentNode.values.set(i, val);
						break;
					} else if (currentNode.times.get(i) > time) {
						currentNode.times.add(i,time);
						currentNode.values.add(i, val);
						break;
					}
				}
				if (!foundTime) {
					currentNode.times.add(time);
					currentNode.values.add(val);
				}
				break;
			}
			prevNode = currentNode;
			currentNode = currentNode.next;
		}
		if (firstNode == null) {
			firstNode = new Node<>(key,val,time);
		}
		else if (!foundNode) {
			prevNode.next = new Node<>(key, val, time);
		}
	}
	
	public V get(K key, int time) {
		Node<K,V> currentNode = firstNode;
		while (currentNode != null) {
			if (currentNode.key == key) {
				for (int i=0; i < currentNode.times.size(); i++) {
					if (currentNode.times.get(i) == time) {
						return currentNode.values.get(i);
					} else if (i != 0 && currentNode.times.get(i) > time) {
						return currentNode.values.get(i-1);
					}
				}
				int lastTime = currentNode.times.get(currentNode.times.size()-1);
				if (time > lastTime) {
					// return value for last time if the query time is past any that has been recorded
					return currentNode.values.get(currentNode.values.size()-1);
				}
			}
			currentNode = currentNode.next;
		}
		return null;
	}
	
	@Test
	public void defaultTest() {
		TimeMap<Integer,Integer> d = new TimeMap<>();
		d.set(1, 1, 0);
		assertEquals(Integer.valueOf(1), d.get(1, 0));
		d.set(1, 2, 2);
		assertEquals(Integer.valueOf(2), d.get(1, 2));
		assertEquals(Integer.valueOf(1), d.get(1, 1));
		d.get(1, 3);
		assertEquals(Integer.valueOf(2), d.get(1, 3));
		d.set(1, 1, 5);
		// don't agree with this case...
		//assertNull(d.get(1, 0));
		assertEquals(Integer.valueOf(1), d.get(1, 10));
		d.set(1, 1, 0);
		d.set(1, 2, 0);
		assertEquals(Integer.valueOf(2), d.get(1, 0));
	}

}
