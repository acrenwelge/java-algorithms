package ocp.datetime;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public class DateTimeConstants {

	public static void main(String[] args) {
		TemporalUnit[] units = {
				ChronoUnit.NANOS, ChronoUnit.MICROS, ChronoUnit.MILLIS,
				ChronoUnit.SECONDS, ChronoUnit.MINUTES, ChronoUnit.HOURS,
				ChronoUnit.HALF_DAYS, ChronoUnit.DAYS, ChronoUnit.WEEKS,
				ChronoUnit.MONTHS, ChronoUnit.YEARS, ChronoUnit.DECADES,
				ChronoUnit.CENTURIES, ChronoUnit.MILLENNIA, ChronoUnit.ERAS, ChronoUnit.FOREVER
		};
		TemporalField[] fields = { ChronoField.NANO_OF_SECOND, ChronoField.MILLI_OF_DAY,
				ChronoField.SECOND_OF_DAY, ChronoField.MINUTE_OF_DAY, ChronoField.HOUR_OF_DAY,
				ChronoField.DAY_OF_WEEK, ChronoField.DAY_OF_MONTH, ChronoField.MONTH_OF_YEAR,
				ChronoField.YEAR
		};
		System.out.println(units);
		System.out.println(fields);
	}

}
