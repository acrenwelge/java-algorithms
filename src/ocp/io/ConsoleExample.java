package ocp.io;

import java.io.Console;

public class ConsoleExample {

	public static void main(String[] args) {
		Console con = System.console();
		String username = con.readLine("Enter username: ");
		char[] pwd = con.readPassword("Enter password: ");
		System.out.println(username);
		System.out.println(pwd);
	}

}
