package ocp.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
        static final Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		Path p1 = Paths.get("/Users/Andrew/Desktop/");
		Path p2 = Paths.get("/Users/Andrew/Documents/");
		log.debug(p1.subpath(0, 3));
		Path p3 = Paths.get(p2.getRoot().toString(), p1.subpath(0, 3).toString());
		log.debug(p3);
	}

}
