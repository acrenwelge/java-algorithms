package ocp.datetime;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomDateFormatter {
        static final Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			log.debug("Enter a date format");
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
			log.debug(formatted);
		}
		scan.close();
	}

}
