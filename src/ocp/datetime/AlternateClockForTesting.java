package ocp.datetime;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AlternateClockForTesting {
	
	public static void main(String[] args) {
		LocalDateTime tenDaysAgo = LocalDateTime.now(Clock.offset(Clock.systemUTC(), Duration.ofDays(-10)));
		LocalDateTime epoch = LocalDateTime.now(Clock.fixed(Instant.EPOCH, ZoneId.systemDefault()));
		LocalDateTime beginTime = LocalDateTime.now(Clock.fixed(Instant.MIN.plus(Duration.ofDays(700)), ZoneId.systemDefault()));
		LocalDateTime endTime = LocalDateTime.now(Clock.fixed(Instant.MAX.minus(Duration.ofDays(700)), ZoneId.systemDefault()));
		System.out.println(tenDaysAgo);
		System.out.println(epoch);
		System.out.println(beginTime);
		System.out.println(endTime);
	}
	
}
