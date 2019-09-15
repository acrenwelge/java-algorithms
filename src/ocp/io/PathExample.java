package ocp.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {

	public static void main(String[] args) {
		Path p1 = Paths.get("/Users/Andrew/Desktop/");
		Path p2 = Paths.get("/Users/Andrew/Documents/");
		System.out.println(p1.subpath(0, 3));
		Path p3 = Paths.get(p2.getRoot().toString(), p1.subpath(0, 3).toString());
		System.out.println(p3);
	}

}
