package problems.medium;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Given an unordered list of flights taken by someone, each represented as (origin, destination) pairs, and a starting airport,
 * compute the person's itinerary. If no such itinerary exists, return null. If there are multiple possible itineraries, 
 * return the lexicographically smallest one. All flights must be used in the itinerary.

For example, given the list of flights [('SFO', 'HKO'), ('YYZ', 'SFO'), ('YUL', 'YYZ'), ('HKO', 'ORD')] and 
starting airport 'YUL', you should return the list ['YUL', 'YYZ', 'SFO', 'HKO', 'ORD'].

Given the list of flights [('SFO', 'COM'), ('COM', 'YYZ')] and starting airport 'COM', you should return null.

Given the list of flights [('A', 'B'), ('A', 'C'), ('B', 'C'), ('C', 'A')] and starting airport 'A', you should 
return the list ['A', 'B', 'C', 'A', 'C'] even though ['A', 'C', 'A', 'B', 'C'] is also a valid itinerary. 
However, the first one is lexicographically smaller.
 * 
 */
public class FlightItinerary {
	
	private Logger log = LogManager.getLogger(FlightItinerary.class);
	
	static class Flight {
		String origin;
		String dest;
		public Flight(String or, String d) {
			this.origin = or;
			this.dest = d;
		}
	}
	
	/*
	 * (WRONG) Approach:
	 *  - use Map to map origins to list of possible destinations (we can use TreeMap / TreeSet to keep order)
	 *  - also store a Set of all airports
	 *  - start at origin in the map, go to next destination
	 *    - grab first one, look up the destination in the map, 
	 *    remove current destination from map (add to returned list) and continue
	 *    - loop for the next destination
	 *      - if we run out of destinations and haven't hit all airports (set not empty), 
	 *      go back and remove destination from the Map
	 *      - if we are at the origin and trying to go back, return null
	 *  - this algorithm uses improper end condition - returns when all locations have been reached
	 *  instead of all flights have been taken (READ THE PROBLEM STATEMENT)
	 */
	public String[] incorrect(List<Flight> flights, String start) {
		Map<String, Set<String>> destinations = new TreeMap<>();
		Set<String> places = new HashSet<>();
		for (Flight f : flights) {
			places.add(f.origin);
			places.add(f.dest);
			if (destinations.containsKey(f.origin)) {
				destinations.get(f.origin).add(f.dest);
			} else {
				destinations.put(f.origin, new TreeSet<>());
				destinations.get(f.origin).add(f.dest);
			}
		}
		String goToNext = start;
		String current = null;
		List<String> itinerary = new ArrayList<>();
		while(!itinerary.containsAll(places)) {
			log.debug("ANOTHER LOOP, itinerary: " + itinerary);
			log.debug("Going to " + goToNext + " next");
			Set<String> dests = destinations.get(goToNext);
			if (current == null && (dests == null || dests.isEmpty())) {
				log.debug("RETURNING NULL");
				return null; // can't go back any further!
			}
			if (dests == null || dests.isEmpty()) { // could be at the end or beginning
				itinerary.add(goToNext);
				if (itinerary.containsAll(places)) { // next destination is last place
					return itinerary.toArray(new String[0]);
				} else { // go back
					itinerary.remove(goToNext); // undo that!
					log.debug("GOING BACK");
					goToNext = current;
					itinerary.remove(current);
					current = itinerary.isEmpty() ? null : itinerary.get(itinerary.size()-1);
				}
			} else { // go forward
				log.debug("GOING FORWARD");
				current = goToNext;
				itinerary.add(current);
				goToNext = dests.iterator().next(); // just grab the first one (should be in order lexigraphically)
				dests.remove(goToNext); // if we go back, we shouldn't go to the same place again (prune the possible next steps)
			}
		}
		return null;
	}
	
	/*
	 * New Approach:
	 *  - Generate flight Map of origins to set of destinations (use TreeSet for ordering)
	 *  - Keep running itinerary
	 *  - Start from given origin (do initial check that it exists in flight map)
	 *  - While the Map is not empty...
	 *    - Add current airport to itinerary
	 *    - Grab first airport from set of destinations
	 *    - Check that going to next destination is possible
	 *      - If not, go back (remove last itinerary)
	 *      - If so, remove flight mapping and continue
	 *  TODO: fix bug that occurs when you need to use the flights in a different order than in which they are traversed
	 *  (currently we just remove the mapping from the map so it can never be reached again)
	 */
	public String[] computeItinerary(List<Flight> flights, String start) {
		if (flights == null || start == null) return null;
		Map<String, Set<String>> flightMap = new HashMap<>();
		for (Flight f : flights) {
			flightMap.putIfAbsent(f.origin, new TreeSet<>());
			flightMap.get(f.origin).add(f.dest);
		}
		// use unmodifiable map to keep track of available flight paths
		final Map<String, Set<String>> immutableflightMap = Collections.unmodifiableMap(flightMap);
		log.debug("IMMUTABLE: " +immutableflightMap);
		List<String> itinerary = new ArrayList<>();
		String current = start;
		while (!flightMap.isEmpty()) {
			log.debug("ANOTHER LOOP, itinerary: " + itinerary);
			log.debug("FLIGHT MAP: " + flightMap);
			log.debug(current);
			Set<String> dests = immutableflightMap.getOrDefault(current, Collections.emptySet());
			if (dests.isEmpty()) { // no more destinations - go back
				log.debug("GO BACK");
				// remove from itinerary
				if (!itinerary.isEmpty()) {
					current = itinerary.remove(itinerary.size()-1);
				} else {
					return null;
				}
			} else { // go forward
				log.debug("GO FORWARD");
				itinerary.add(current);
				String prev = current;
				current = dests.iterator().next();
				Set<String> modifiableDests = flightMap.get(prev);
				modifiableDests.remove(current); // remove to satisfy exit condition and so we don't come back to it later
				log.debug(modifiableDests);
				if (modifiableDests.isEmpty()) flightMap.remove(prev); // if it's the last entry, also remove set from the mutable map
				if (flightMap.isEmpty()) { // just add the last destination since we're about to exit
					itinerary.add(current); // this is fine because we already know we can get to this airport
				}
			}
		}
		return itinerary.toArray(new String[0]);
	}
	
	@Before
	public void msg() {
		log.debug("-------NEW TEST-------");
	}
	
	@Test
	public void defaultTest() {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("SFO","COM"));
		flights.add(new Flight("COM","YYZ"));
		assertNull(computeItinerary(flights,"COM"));
	}
	
	@Test
	public void defaultTest2() {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("A","B"));
		flights.add(new Flight("A","C"));
		flights.add(new Flight("B","C"));
		flights.add(new Flight("C","A"));
		assertArrayEquals(new String[] {"A","B","C","A","C"},computeItinerary(flights,"A"));
	}
	
	@Test
	public void defaultTest3() {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("SFO","HKO"));
		flights.add(new Flight("YYZ","SFO"));
		flights.add(new Flight("YUL","YYZ"));
		flights.add(new Flight("HKO","ORD"));
		assertArrayEquals(new String[] {"YUL", "YYZ", "SFO", "HKO", "ORD"},computeItinerary(flights,"YUL"));
	}
	
	@Test
	public void customTest() {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("A","C"));
		flights.add(new Flight("B","D"));
		flights.add(new Flight("C","A"));
		flights.add(new Flight("D","B"));
		assertNull(computeItinerary(flights,"A"));
		assertNull(computeItinerary(flights,"D"));
	}
	
	@Test
	@Ignore
	public void customTest2() {
		/*
		 * Flights:
		 * A -> B -> C -> D -> A -> C -> B
		 */
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("A","B"));
		flights.add(new Flight("B","C"));
		flights.add(new Flight("C","D"));
		flights.add(new Flight("D","A"));
		flights.add(new Flight("A","C"));
		flights.add(new Flight("C","B"));
		assertArrayEquals(new String[] {"A","B","C","D","A","C","B"},computeItinerary(flights,"A"));
	}
}
