package problems.easy;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Implement a stack that has the following methods:

- push(val), which pushes an element onto the stack
- pop(), which pops off and returns the topmost element of the stack. 
If there are no elements in the stack, then it should throw an error or return null.
- max(), which returns the maximum value in the stack currently. 
If there are no elements in the stack, then it should throw an error or return null.

Each method should run in constant time.
 * @author Andrew
 */
@SuppressWarnings("unchecked")
public class Stack<T> {
	static final Logger log = LogManager.getLogger(Stack.class);
	
	private T[] contents;
	
	public Stack() {
		this.contents = (T[]) new Object[0];
	}
	
	public void push(T el) {
		T[] newArr = (T[]) new Object[this.contents.length+1];
		System.arraycopy(this.contents, 0, newArr, 0, this.contents.length);
		newArr[this.contents.length] = el;
		this.contents = newArr;
	}
	
	public T pop() {
		if (this.contents.length == 0) throw new RuntimeException("Empty stack");
		T[] truncated = (T[]) new Object[this.contents.length-1];
		System.arraycopy(this.contents, 0, truncated, 0, this.contents.length-1);
		T popped = this.contents[this.contents.length-1];
		this.contents = truncated;
		return popped;
	}
	
	public T max() {
		if (this.contents.length == 0) throw new RuntimeException("Empty stack");
		T max = contents[0];
		for (T el : contents) {
			if (el instanceof Comparable) {
				if (((Comparable<T>) el).compareTo(max) > 0) {
					max = el;
				};
			}
		}
		return max;
	}
	
	@Test(expected=RuntimeException.class)
	public void myStringTest() {
		Stack<String> strStack = new Stack<>();
		strStack.push("Hi");
		strStack.push("Hello");
		strStack.push("Goodbye");
		log.debug(strStack.max());
		log.debug(strStack.pop());
		log.debug(strStack.pop());
		log.debug(strStack.pop());
		log.debug(strStack.pop());
	}
	
	@Test
	public void myIntegerTest() {
		Stack<Integer> intStack = new Stack<>();
		intStack.push(1);
		intStack.push(4);
		intStack.push(-2);
		assertEquals(new Integer(4),intStack.max());
		assertEquals(new Integer(-2),intStack.pop());
	}
}
