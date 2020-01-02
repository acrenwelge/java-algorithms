package ocp.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Console;

public class ConsoleExample {
        static final Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		Console con = System.console();
		String username = con.readLine("Enter username: ");
		char[] pwd = con.readPassword("Enter password: ");
		log.debug(username);
		log.debug(pwd);
	}

}
