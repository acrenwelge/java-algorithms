package ocp.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ReadWriteFile {
	
	public static final String filePath = "TESTFILE.txt";

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
		List<String> lines = Files.readAllLines(Paths.get("README.md"));
		lines.forEach(System.out::print);
		System.out.println();
		double count = lines.stream()
		  .filter(s -> Boolean.logicalOr(s.contains("java"),s.contains("Java")))
		  .count();
		System.out.println(count);
	}
	
	public static void writeFile() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE);
		writer.append("First line");
		writer.newLine();
		writer.append("Second line");
		writer.close();
		BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
		String s = null;
		while ((s = reader.readLine()) != null) {
			System.out.println(s);
		}
	}
	
	public static void deleteFile() throws IOException {
		Files.deleteIfExists(Paths.get(filePath));
	}
}