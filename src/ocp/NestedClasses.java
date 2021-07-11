package ocp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NestedClasses {
        static final Logger log = LogManager.getRootLogger();

	int value = 5;

	public static class NestedStaticClass {
		// variable hiding
		static int value = 3;

		public static void testMe() {
			log.debug(value);
		}
	}

	public class InnerClass {
		int value = 8;
		// cannot declare static variable in non-static inner class
		//static int val = 1;

		public void testMe() {
			log.debug(value);
			log.debug(NestedClasses.this.value);
		}
	}

	public static void main(String[] args) {
		NestedStaticClass.testMe();
		new NestedClasses().new InnerClass().testMe();
	}
}
