package problems.medium;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * Given a function that generates perfectly random numbers between 1 and k (inclusive), 
 * where k is an input, write a function that shuffles a deck of cards represented as an array using only swaps.

It should run in O(N) time.

Hint: Make sure each one of the 52! permutations of the deck is equally likely.
 * @author Andrew
 *
 */
public class ShuffleDeck {
	
	private Random r = new Random();
	
	static class Card {
		int val;
		String suit;
		
		public Card(int val, String suit) {
			this.val = val;
			this.suit = suit;
		}
		
		@Override
		public String toString() {
			if (val == 10) {
				return "Jack of " + suit;
			} else if (val == 11) {
				return "Queen of " + suit;
			} else if (val == 12) {
				return "King of " + suit;
			} else if (val == 13) {
				return "Ace of " + suit;
			}
			return val + " of " + suit;
		}
	}
	
	/*
	 * Approach:
	 * - generate the deck
	 * - swap every card with another card chosen at random
	 */
	public Card[] shuffle() {
		// generate the deck
		Card[] deck = new Card[52];
		int suit = 0;
		for (String s : new String[] {"Hearts", "Spades", "Clubs", "Diamonds"}) {
			for (int i=1;i<=13;i++) {
				deck[i+(suit*13)-1] = new Card(i,s);
			}
			suit++;
		}
		
		// apply the algorithm
		for (int i=0; i < deck.length; i++) {
			int swapIdx = getRand(deck.length);
			Card tmp = deck[swapIdx];
			deck[swapIdx] = deck[i];
			deck[i] = tmp;
		}
		return deck;
	}
	
	public int getRand(int k) {
		return r.nextInt(k-1) + 1;
	}
	
	@Test
	/*
	 * Testing approach:
	 * - generate N shuffled decks
	 * - expect 
	 */
	public void defaultTest() {
		int n = 1000;
		Card[][] decks = new Card[n][52];
		int i = 0;
		while (i < n) {
			decks[i] = shuffle();
			System.out.println(Arrays.toString(decks[i]));
			i++;
		}
		fail("How can you test this...");
	}
}
