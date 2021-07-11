package problems.medium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Test;

/**
 * This problem was asked by Apple.
 *
Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
 * @author Andrew
 *
 */
public class Scheduler {
        static final Logger log = LogManager.getRootLogger();

	public void scheduler(Runnable f, int n) {
		Runnable wait = () -> {
			try {
				log.debug("Thread started");
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
		scheduler(() -> log.debug("It worked"), 2000);
	}
}
