package problems.medium;

import org.junit.Test;

/**
 * This problem was asked by Apple.
 * 
Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
 * @author Andrew
 *
 */
public class Scheduler {
	
	public void scheduler(Runnable f, int n) {
		Runnable wait = () -> {
			try {
				System.out.println("Thread started");
				Thread.sleep(n);
				f.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		wait.run();
	}
	
	@Test
	public void runs() throws InterruptedException {
		scheduler(() -> System.out.println("It worked"), 2000);
	}
}
