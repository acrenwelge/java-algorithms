package problems.medium;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Implement a queue using two stacks. Recall that a queue is a FIFO (first-in, first-out) 
 * data structure with the following methods: enqueue, which inserts an element into 
 * the queue, and dequeue, which removes it.
 * @author Andrew
 *
 */
public class Queue<E> {
	private static final Logger log = LogManager.getLogger(Queue.class);
	
	private static final int INIT_SIZE = 10;
	Object[] elements = new Object[INIT_SIZE];
	int tail = INIT_SIZE-1;
	int head = INIT_SIZE-1;
	
	public void enqueue(E el) {
		if (tail - 1 < 0) {
			// max size reached
			Object[] newElArr = new Object[elements.length * 2];
			tail = Math.floorDiv(newElArr.length,2);
			head = newElArr.length-1;
			System.arraycopy(elements, 0, newElArr, tail, elements.length);
			this.elements = newElArr;
		}
		elements[tail] = el;
		tail--;
	}
	
	@SuppressWarnings("unchecked") // no way to avoid using Object[] because generic arrays not permitted
	public E dequeue() {
		return (E) elements[head--];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Object o : elements) {
			if (o == null) {
				sb.append("null ");
			} else {
				sb.append(o.toString());
			}
		}
		return sb.toString();
	}
	
	@Test
	public void enqueueDeuqueStringTest() {
		Queue<String> myStringQueue = new Queue<>();
		myStringQueue.enqueue("hello");
		assertEquals("hello",myStringQueue.dequeue());
	}
	
	@Test
	public void resizingQueueTest() {
		Queue<String> myStringQueue = new Queue<>();
		for (int i = 0;i < INIT_SIZE * 4; i++) {
			if (i > 30) log.debug(myStringQueue);
			myStringQueue.enqueue("String " + i);
		}
		for (int i = 0;i < INIT_SIZE * 4; i++) {
			assertEquals("String "+i, myStringQueue.dequeue());
		}
	}
}
