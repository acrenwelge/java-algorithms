package ocp.io;

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
	
	public static final Scanner sc = new Scanner(System.in);
	
	static final Path currentDir = Paths.get("");
	
	public static void main(String[] args) {
	  while(true) {
		  try {
			int choice = printMenu();
			int choiceOpt = 1;
		  	if (choice == choiceOpt) {
		  		System.out.println("List recursively? (y/n)");
		  		String input = sc.nextLine();
		  		if (input.equalsIgnoreCase("y")) {
		  			Files.walk(currentDir).forEachOrdered(System.out::println);
		  		} else {
		  			Stream<Path> paths = Files.list(currentDir);
		  			paths.forEachOrdered((p) -> System.out.println(p.getFileName()));
		  			paths.close();
		  		}
		  	} else if (choice == ++choiceOpt) {
		  		System.out.println("Enter the file name: ");
		  		String fileName = sc.nextLine();
		  		Files.createFile(Paths.get(fileName));
		  	} else if (choice == ++choiceOpt) {
		  		System.out.println("Enter the file to write to:");
		  		try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(sc.nextLine()),
		  				StandardOpenOption.CREATE,
		  				StandardOpenOption.APPEND)) {
			  		while(true) {
			  			System.out.println("Text to write to file: ");
			  			String input = sc.nextLine();
			  			if (input.equals("exit")) break;
			  			else bw.write(input + System.lineSeparator());
			  		}
		  		}
		  	} else if (choice == ++choiceOpt) {
		  		System.out.println("Enter file to read from:");
		  		List<String> lines = Files.readAllLines(Paths.get(sc.nextLine()));
		  		System.out.println("TOTAL LINES: " + lines.size());
		  		System.out.println("LINES WITH 'JAVA': " 
		  		  + lines.stream().filter((l) -> l.contains("Java")).count());
		  		lines.stream().forEachOrdered(System.out::println);
		  	} else if (choice == ++choiceOpt) {
		  		System.out.println("Source file:");
		  		String source = sc.nextLine();
		  		System.out.println("Target file:");
		  		String target = sc.nextLine();
		  		Files.copy(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
		  	} else if (choice == ++choiceOpt) {
		  		System.out.println("File to move:");
		  		String file = sc.nextLine();
		  		System.out.println("Where to move to:");
		  		Files.move(Paths.get(file), Paths.get(sc.nextLine()));
		  	} else if (choice == ++choiceOpt) {
		  		System.out.println("File to delete:");
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
		System.out.println("1 - List contents of directory");
		System.out.println("2 - Create new file");
		System.out.println("3 - Write to file");
		System.out.println("4 - Read a file");
		System.out.println("5 - Copy a file");
		System.out.println("6 - Move/rename a file");
		System.out.println("7 - Delete a file or directory");
		return Integer.parseInt(sc.nextLine());
	}
}
