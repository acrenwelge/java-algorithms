package ocp.io;

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

public class FileVisitorExample {
	
	public static class PrinterVisitor extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob: **/*.java"); // look for this file!
			if (pm.matches(file)) {
				System.out.println("here");
				System.out.println(file.getFileName());
				return FileVisitResult.TERMINATE;
			}
			System.out.println("OWNER: " + Files.getOwner(file));
			if (Files.isHidden(file)) {
				System.out.println(file.getFileName() + " IS HIDDEN");
				return FileVisitResult.SKIP_SIBLINGS;
			} else if (Files.isExecutable(file)) {
				System.out.println(file.getFileName() + " IS EXECUTABLE");
			} else if (Files.isRegularFile(file)) {
				System.out.println(file.getFileName() + " IS REGULAR FILE");
			}
			return FileVisitResult.CONTINUE;
		}
		
	}

	public static void main(String[] args) throws IOException {
		Path p = Paths.get("");
		FileVisitor<Path> visitor = new PrinterVisitor();
		Files.walkFileTree(p, visitor);
	}

}
