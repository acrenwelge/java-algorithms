package ocp.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Interactive program to ask the user to either:
 * (a) list contents of current directory
 *   - either just the current, or recursively
 * (b) create a new file in current directory
 *   - enter the file name
 * (c) write to a file
 *   - enter text to write
 * (d) copy a file
 *   - provide the name of the copied file
 * (e) move a file
 *   - provide the new name
 * (f) delete the file or directory
 *   - ask to delete recursively
 *   - ask to delete if not empty
 * @author Andrew
 *
 */
public class ManipulateFilesExample {
        static final Logger log = LogManager.getRootLogger();

	public static final Scanner sc = new Scanner(System.in);

	static final Path currentDir = Paths.get("");

	public static void main(String[] args) {
	  while(true) {
		  try {
			int choice = printMenu();
			int choiceOpt = 1;
		  	if (choice == choiceOpt) {
		  		log.debug("List recursively? (y/n)");
		  		String input = sc.nextLine();
		  		if (input.equalsIgnoreCase("y")) {
		  			Files.walk(currentDir).forEachOrdered(System.out::println);
		  		} else {
		  			Stream<Path> paths = Files.list(currentDir);
		  			paths.forEachOrdered((p) -> log.debug(p.getFileName()));
		  			paths.close();
		  		}
		  	} else if (choice == ++choiceOpt) {
		  		log.debug("Enter the file name: ");
		  		String fileName = sc.nextLine();
		  		Files.createFile(Paths.get(fileName));
		  	} else if (choice == ++choiceOpt) {
		  		log.debug("Enter the file to write to:");
		  		try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(sc.nextLine()),
		  				StandardOpenOption.CREATE,
		  				StandardOpenOption.APPEND)) {
			  		while(true) {
			  			log.debug("Text to write to file: ");
			  			String input = sc.nextLine();
			  			if (input.equals("exit")) break;
			  			else bw.write(input + System.lineSeparator());
			  		}
		  		}
		  	} else if (choice == ++choiceOpt) {
		  		log.debug("Enter file to read from:");
		  		List<String> lines = Files.readAllLines(Paths.get(sc.nextLine()));
		  		log.debug("TOTAL LINES: " + lines.size());
		  		log.debug("LINES WITH 'JAVA': "
		  		  + lines.stream().filter((l) -> l.contains("Java")).count());
		  		lines.stream().forEachOrdered(System.out::println);
		  	} else if (choice == ++choiceOpt) {
		  		log.debug("Source file:");
		  		String source = sc.nextLine();
		  		log.debug("Target file:");
		  		String target = sc.nextLine();
		  		Files.copy(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
		  	} else if (choice == ++choiceOpt) {
		  		log.debug("File to move:");
		  		String file = sc.nextLine();
		  		log.debug("Where to move to:");
		  		Files.move(Paths.get(file), Paths.get(sc.nextLine()));
		  	} else if (choice == ++choiceOpt) {
		  		log.debug("File to delete:");
		  		String file = sc.nextLine();
		  		Files.delete(Paths.get(file));
		  	} else {
		  		break;
		  	}
		  } catch (FileAlreadyExistsException fae) {
			  System.err.println("Already exists!");
		  } catch (IOException ioe) {
			  System.err.println("Something went wrong - start again");
		  }
	  }
	}

	private static int printMenu() {
		log.debug("1 - List contents of directory");
		log.debug("2 - Create new file");
		log.debug("3 - Write to file");
		log.debug("4 - Read a file");
		log.debug("5 - Copy a file");
		log.debug("6 - Move/rename a file");
		log.debug("7 - Delete a file or directory");
		return Integer.parseInt(sc.nextLine());
	}
}
