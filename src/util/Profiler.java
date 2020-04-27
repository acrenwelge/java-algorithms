package util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public interface Profiler {
	
	static final Logger logger = LogManager.getLogger(Profiler.class);
	
	public static void profile(String name, Runnable r) {
		long start = System.currentTimeMillis();
		r.run();
		long end = System.currentTimeMillis();
		logger.info(String.format("%s took %s millis to run", name, (end - start)));
	}
}
