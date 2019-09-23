package ocp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;

public class CollectionExercises {
	static class Dummy implements Comparable<Dummy> {
		int age;
		int id;
		
		@Override
		public boolean equals(Object obj) {
			if (obj.getClass() != this.getClass()) return false;
			return ((Dummy) obj).age == this.age;
		}
		
		@Override
		public int hashCode() {
			return this.id;
		}
		
		public Dummy(int id, int age) {
			this.id = id;
			this.age = age;
		}

		@Override
		public int compareTo(Dummy d) {
			return Integer.compare(this.id, d.id);
		}
		
		@Override
		public String toString() {
			return "" + this.id + " " + this.age;
		}
	}

	public static void main(String[] args) {
		sets();
		lists();
		maps();
		queues();
	}
	
	public static void sets() {
		Set<Dummy> dummies = new TreeSet<>();
		dummies.add(new Dummy(1, 5));
		dummies.add(new Dummy(2, 5));
		dummies.add(new Dummy(2, 6));
		dummies.add(new Dummy(3, 5));
		System.out.println(dummies);
	}
	
	public static void lists() {
		List<String> names = new ArrayList<>();
		names.add("Andrew");
		names.add("Blake");
		names.add("Seth");
		names.add(1, "Andrea");
		names.set(2, "Bob");
		System.out.println(names);
	}
	
	public static void queues() {
		Queue<String> words = new LinkedList<>();
		words.add("First");
		words.add("Second");
		words.add("Third");
		System.out.println(words.peek()); // retrieves but does not remove First
		System.out.println(words.element()); // also does not remove First
		System.out.println(words.poll()); // removes First
		System.out.println(words.remove()); // removes Second
		System.out.println(words.remove()); // removes Third
		System.out.println(words.poll()); // null
		System.out.println(words.peek()); // null
		try {
			System.out.println(words.remove()); // exception thrown
		} catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(words.element()); // exception thrown
		} catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		
		words = new ArrayBlockingQueue<>(2); // only 2 elements allowed
		boolean a = words.add("First");
		boolean b = words.offer("Second");
		boolean c = words.offer("Third");
		System.out.println("" + a + b + c);
		try {
			words.add("Not allowed");
		} catch (IllegalStateException ise) {
			ise.printStackTrace();
		}
	}
	
	public static void maps() {
		Map<String, Integer> scores = new HashMap<>();
		scores.put("Andrew", 10);
		scores.put("Blake", 3);
		scores.put("Charlie", 6);
		scores.put("Nicole", 5);
		SortedMap<String, Integer> alsoScores = new TreeMap<>();
		alsoScores.putAll(scores);
		SortedMap<String, Integer> sub = alsoScores.subMap("Blake", "Delta");
		sub.values().stream().forEach(System.out::println); // 3, 6
		sub.put("Charlie", 7);
		System.out.println(alsoScores.get("Charlie")); // 7
	}

}
