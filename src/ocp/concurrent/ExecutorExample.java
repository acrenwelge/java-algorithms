package ocp.concurrent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {
        static final Logger log = LogManager.getRootLogger();
	static Runnable runMe = () -> {
		log.debug(Thread.currentThread().getName());
	};

	static Callable<String> callMe = () -> {
		return "maybe?";
	};

	/**
	 * 3 main interfaces:
	 * - Executor (simple replacement for launching threads manually)
	 * - ExecutorService (for lifecycle methods of executor and task)
	 * - ScheduledExecutorService (future/periodic execution)
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		simpleExecutorTypes();
		Future<String> result = null;
		result = executorServiceExample();
		schedule();
		if (result != null) log.debug(result.get());
	}

	public static void simpleExecutorTypes() {
		Executor single = Executors.newSingleThreadExecutor();
		Executor pool   = Executors.newCachedThreadPool();
		Executor fixed  = Executors.newFixedThreadPool(5);
		Executor steal  = Executors.newWorkStealingPool();
		single.execute(runMe);
		pool.execute(runMe);
		fixed.execute(runMe);
		steal.execute(runMe);
	}

	public static Future<String> executorServiceExample() throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newCachedThreadPool();
		// can now use .submit()
		es.submit(runMe);
		Future<String> future = es.submit(callMe);
		return future;
	}

	public static void schedule() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.schedule(runMe, 5, TimeUnit.SECONDS);
		scheduler.scheduleAtFixedRate(runMe, 10, 3, TimeUnit.SECONDS);
	}

}
