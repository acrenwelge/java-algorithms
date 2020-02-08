package problems.easy;

import java.time.Instant;

/**
 * Given a function f, and N return a debounced f of N milliseconds.

That is, as long as the debounced f continues to be invoked, f itself will not be called for N milliseconds.
 * @author Andrew
 */
public class DebouncedFunction {
	
	public static void main(String[] args) {
		Runnable run = () -> System.out.println("hello");
		Runnable debounced = new DebouncedFunction().getDebounced(run, 1000);
		debounced.run();
		debounced.run();
		debounced.run();
	}
	
	private Runnable func;
	private long delayInMillis;
	private Instant called;
	
	public Runnable getDebounced(Runnable r, long delayMillis) {
		func = r;
		delayInMillis = delayMillis;
		called = Instant.now();
		return () -> {
			try {
				called = Instant.now();
				Thread.sleep(delayMillis);
				if (Instant.now().minusMillis(delayInMillis).isAfter(called)) {
					func.run();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	}
}
