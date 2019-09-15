package problems.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This problem was asked by Amazon.

Run-length encoding is a fast and simple method of encoding strings. 
The basic idea is to represent repeated successive characters as a single count and character. 
For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".

Implement run-length encoding and decoding. 
You can assume the string to be encoded have no digits and consists solely of alphabetic characters. 
You can assume the string to be decoded is valid.
 * @author Andrew
 *
 */
public class RunLengthEncoding {
	
	public static String encode(String source) {
		char prev = source.charAt(0);
		int count = 1;
		StringBuilder code = new StringBuilder();
		for (int index=1; index < source.length(); index++) {
			char c = source.charAt(index);
			if (c == prev) {
				count++;
				if (index + 1 == source.length()) {
					// at the last character
					code.append(String.valueOf(count) + String.valueOf(prev));
				}
			} else {
				code.append(String.valueOf(count) + String.valueOf(prev));
				count = 1;
			}
			prev = c;
		}
		return code.toString();
	}
	
	public static String decode(String encoded) {
		StringBuilder source = new StringBuilder();
		String numStr = "";
		for (int i=0; i < encoded.length(); i++) {
			char c = encoded.charAt(i);
			if (Character.isAlphabetic(c)) {
				for (int j = 0; j < Integer.parseInt(numStr); j++) {
					source.append(c);
				}
				numStr = "";
			} else {
				numStr = numStr.concat(String.valueOf(c));
			}
		}
		return source.toString();
	}
	
	@Test
	public void defaultTest() {
		String encodeMe = "AAAABBBCCDAA";
		String encoded = "4A3B2C1D2A";
		assertEquals(encoded, encode(encodeMe));
		assertEquals(encodeMe, decode(encoded));
	}
	
	@Test
	public void customTest() {
		String encodeMe = "AAABBBCDEFGHHHOOPPPPPPLLLAAZZZZ";
		String encoded = "3A3B1C1D1E1F1G3H2O6P3L2A4Z";
		assertEquals(encoded, encode(encodeMe));
		assertEquals(encodeMe, decode(encoded));
	}
}
