package ocp.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomDateFormatter {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Enter a date format");
			String s = scan.nextLine();
			if (s.equals("exit")) break;
			DateTimeFormatter dtf = null;
			try {
				dtf = DateTimeFormatter.ofPattern(s);
			} catch (IllegalArgumentException e) {
				System.err.println("Invalid format - try again");
				continue;
			}
			String formatted = LocalDateTime.now().format(dtf);
			System.out.println(formatted);
		}
		scan.close();
	}

}
