package ocp.datetime;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

public class DateTimeLocalization {
        static final Logger log = LogManager.getRootLogger();

	static LocalDate ld;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		DateTimeLocalization dtd = new DateTimeLocalization();
		while (true) {
			dtd.runProgram();
		}
	}

	public void runProgram() {
		log.debug("Enter a date to start: (format: YYYY-MM-DD)");
		String string = sc.nextLine();
		if (string.equals("exit")) System.exit(0);
		ld = LocalDate.parse(string);
		log.debug("Default date format for your date: " + ld.toString());
		DateTimeFormatter dtf = getDateFormat();
		log.debug(dtf.format(ld));
	}

	public static DateTimeFormatter getDateFormat() {
		// Locale selection
		log.debug("Pick a locale:");
		log.debug("1 - USA");
		log.debug("2 - France");
		log.debug("3 - Canada");
		log.debug("4 - UK");
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
		log.debug("Pick a date format:");
		log.debug("1 - SHORT");
		log.debug("2 - MEDIUM");
		log.debug("3 - LONG");
		log.debug("4 - FULL");
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
