package ocp.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;
import java.util.stream.Stream;

public class FileVisitorExample {
        static final Logger log = LogManager.getRootLogger();

	public static class PrinterVisitor extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob: **/*.java"); // look for this file!
			if (pm.matches(file)) {
				log.debug("here");
				log.debug(file.getFileName());
				return FileVisitResult.TERMINATE;
			}
			log.debug("OWNER: " + Files.getOwner(file));
			if (Files.isHidden(file)) {
				log.debug(file.getFileName() + " IS HIDDEN");
				return FileVisitResult.SKIP_SIBLINGS;
			} else if (Files.isExecutable(file)) {
				log.debug(file.getFileName() + " IS EXECUTABLE");
			} else if (Files.isRegularFile(file)) {
				log.debug(file.getFileName() + " IS REGULAR FILE");
			}
			return FileVisitResult.CONTINUE;
		}

	}

	public static void main(String[] args) throws IOException {
		Path p = Paths.get("");
		FileVisitor<Path> visitor = new PrinterVisitor();
		Files.walkFileTree(p, visitor);

		// alternative way of finding a file
		try(Stream<Path> strPath = Files.find(p, 5,(path,bfa) -> path.endsWith("FileVisitorExample.java"))) {
			Optional<Path> optPath = strPath.findAny();
			optPath.ifPresent(System.out::println);
			optPath.ifPresent((path) -> {
				try {
					log.debug("SIZE: " + Files.getFileStore(path).getTotalSpace());
				} catch(IOException e) {e.printStackTrace();}
			});
		}
	}

}
