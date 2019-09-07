package ocp.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

public class DateTimeLocalization {
	
	static LocalDate ld;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		DateTimeLocalization dtd = new DateTimeLocalization();
		while (true) {
			dtd.runProgram();
		}
	}
	
	public void runProgram() {
		System.out.println("Enter a date to start: (format: YYYY-MM-DD)");
		String string = sc.nextLine();
		if (string.equals("exit")) System.exit(0);
		ld = LocalDate.parse(string);
		System.out.println("Default date format for your date: " + ld.toString());
		DateTimeFormatter dtf = getDateFormat();
		System.out.println(dtf.format(ld));
	}
	
	public static DateTimeFormatter getDateFormat() {
		// Locale selection
		System.out.println("Pick a locale:");
		System.out.println("1 - USA");
		System.out.println("2 - France");
		System.out.println("3 - Canada");
		System.out.println("4 - UK");
		Locale l;
		int choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case 1: l = Locale.US; break;
		case 2: l = Locale.FRANCE; break;
		case 3: l = Locale.CANADA; break;
		case 4: l = Locale.UK; break;
		default: l = Locale.US; break;
		}
		
		// Date length selection
		System.out.println("Pick a date format:");
		System.out.println("1 - SHORT");
		System.out.println("2 - MEDIUM");
		System.out.println("3 - LONG");
		System.out.println("4 - FULL");
		FormatStyle format;
		int formatChoice = Integer.parseInt(sc.nextLine());
		switch (formatChoice) {
		case 1: format = FormatStyle.SHORT; break;
		case 2: format = FormatStyle.MEDIUM; break;
		case 3: format = FormatStyle.LONG; break;
		case 4: format = FormatStyle.FULL; break;
		default: format = FormatStyle.FULL;
		}
		
		return DateTimeFormatter.ofLocalizedDate(format).withLocale(l);
	}
}
