package ocp.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

	public static void main(String[] args) throws IOException {
		Path p = Paths.get("README.md");
		List<String> lines = Files.readAllLines(p);
		lines.forEach(System.out::print);
		double count = lines.stream()
		  .filter(s -> Boolean.logicalOr(s.contains("java"),s.contains("Java")))
		  .count();
		System.out.println(count);
		List<Integer> list = new ArrayList<>();
		for (int i=0;i<1_000_000;i++) {
			list.add(i);
		}
		long t1 = System.currentTimeMillis();
		double d1 = list.stream().parallel().mapToInt(Integer::intValue).average().getAsDouble();
		long t2 = System.currentTimeMillis();
		double d2 = list.stream().mapToInt(Integer::intValue).average().getAsDouble();
		long t3 = System.currentTimeMillis();
		System.out.println(d1);
		System.out.println(d2);
		System.out.println("Sequential: "+(t3-t2));
		System.out.println("Parallel: "+(t2-t1));
	}
	
}