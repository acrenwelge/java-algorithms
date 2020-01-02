package ocp.datetime;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAmount;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdjustDateTime {
        static final Logger log = LogManager.getRootLogger();

	static LocalDateTime ldt;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ldt = LocalDateTime.now();
		System.out.println("Date & Time is now: " + ldt);
		while (true) {
			printMenu();
			System.out.println("Date & Time is now: " + ldt);
		}
	}

	private static void printMenu() {
		System.out.println("Adjust the date / time:");
		System.out.println("1 - Add / subtract years");
		System.out.println("2 - Add / subtract months");
		System.out.println("3 - Add / subtract days");
		System.out.println("4 - Add / subtract hours");
		System.out.println("5 - Add / subtract minutes");
		System.out.println("6 - Add / subtract seconds");
		System.out.println();
		int input = 0;
		while (true) {
			try {
				input = sc.nextInt();
				if (input >= 1 && input <= 6) break;
				else System.err.println("Please enter a number in the range");
			} catch (InputMismatchException ime) {
				ime.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("Enter amount to add / subtract (negative values are subtracted)");
		int amount = 0;
		while (true) {
			try {
				amount = sc.nextInt();
				break;
			} catch (InputMismatchException ime) {
				ime.printStackTrace();
			}
		}
		ChronoField selection = null;
		switch(input) {
		case 1: selection = ChronoField.YEAR; break;
		case 2: selection = ChronoField.MONTH_OF_YEAR; break;
		case 3: selection = ChronoField.DAY_OF_YEAR; break;
		case 4: selection = ChronoField.HOUR_OF_DAY; break;
		case 5: selection = ChronoField.MINUTE_OF_DAY; break;
		case 6: selection = ChronoField.SECOND_OF_DAY; break;
		}
		adjust(selection, amount);
	}

	private static void adjust(ChronoField field, int amount) {
		TemporalAmount adjust = Duration.ofSeconds(0);
		if (field.equals(ChronoField.YEAR)) adjust = Period.ofYears(amount);
		else if (field.equals(ChronoField.MONTH_OF_YEAR)) adjust = Period.ofMonths(amount);
		else if (field.equals(ChronoField.DAY_OF_YEAR)) adjust = Period.ofDays(amount);
		else if (field.equals(ChronoField.HOUR_OF_DAY)) adjust = Duration.ofHours(amount);
		else if (field.equals(ChronoField.MINUTE_OF_DAY)) adjust = Duration.ofMinutes(amount);
		else if (field.equals(ChronoField.SECOND_OF_DAY)) adjust = Duration.ofSeconds(amount);
		ldt = ldt.plus(adjust);
	}

}
