package ocp.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class AnalyzeFile {
        static final Logger log = LogManager.getRootLogger();

	public static final String SEARCH_FOR = "Java";
	public static final String FILE = "README.md";

	/**
	 * Computes the average length of lines from file above which contain the keyword
	 */
	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get(FILE));
		OptionalDouble avg = lines
				.filter(line -> line.contains(SEARCH_FOR))
				.mapToDouble(String::length)
				.average();
		lines.close();
		if (avg.isPresent()) {
			log.debug(String.format(
					"In file %s lines that contains %s are on average %.2f characters long",
					FILE, SEARCH_FOR, avg.getAsDouble()));
		}
	}

}
