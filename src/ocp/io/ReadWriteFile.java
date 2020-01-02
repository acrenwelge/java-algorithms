package ocp.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class ReadWriteFile {
        static final Logger log = LogManager.getRootLogger();

	public static final String writeToPath = "TESTFILE.txt";
	public static final String readFromPath = "README.md";

	public static void main(String[] args) {
		try {
			readFile();
			writeFile();
			deleteFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFile() throws IOException {
		// readAllLines returns list, lines returns stream
		List<String> lines = Files.readAllLines(Paths.get(readFromPath));
		Stream<String> streamFile = Files.lines(Paths.get(readFromPath));
		lines.forEach(System.out::print);
		System.out.println();
		double count = lines.stream()
		  .filter(s -> Boolean.logicalOr(s.contains("java"),s.contains("Java")))
		  .count();
		System.out.println("Lines with 'Java': " + count);
		System.out.println("TOTAL LINES: " +streamFile.count());
		streamFile.close();
	}

	public static void writeFile() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(writeToPath), StandardOpenOption.CREATE);
		writer.append("First line");
		writer.newLine();
		writer.append("Second line");
		writer.close();
		BufferedReader reader = Files.newBufferedReader(Paths.get(writeToPath));
		String s = null;
		while ((s = reader.readLine()) != null) {
			System.out.println(s);
		}
	}

	public static void deleteFile() throws IOException {
		Files.deleteIfExists(Paths.get(writeToPath));
	}
}
