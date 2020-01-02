package ocp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
        static final Logger log = LogManager.getRootLogger();

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
		log.debug(dummies);
	}

	public static void lists() {
		List<String> names = new ArrayList<>();
		names.add("Andrew");
		names.add("Blake");
		names.add("Seth");
		names.add(1, "Andrea");
		names.set(2, "Bob");
		log.debug(names);
	}

	public static void queues() {
		Queue<String> words = new LinkedList<>();
		words.add("First");
		words.add("Second");
		words.add("Third");
		log.debug(words.peek()); // retrieves but does not remove First
		log.debug(words.element()); // also does not remove First
		log.debug(words.poll()); // removes First
		log.debug(words.remove()); // removes Second
		log.debug(words.remove()); // removes Third
		log.debug(words.poll()); // null
		log.debug(words.peek()); // null
		try {
			log.debug(words.remove()); // exception thrown
		} catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		try {
			log.debug(words.element()); // exception thrown
		} catch(NoSuchElementException e) {
			e.printStackTrace();
		}

		words = new ArrayBlockingQueue<>(2); // only 2 elements allowed
		boolean a = words.add("First");
		boolean b = words.offer("Second");
		boolean c = words.offer("Third");
		log.debug("" + a + b + c);
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
		log.debug(alsoScores.get("Charlie")); // 7
	}

}
