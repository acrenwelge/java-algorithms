package ocp;

public class NestedClasses {
	
	int value = 5;
	
	public static class NestedStaticClass {
		// variable hiding
		static int value = 3;
		
		public static void testMe() {
			System.out.println(value);
		}
	}
	
	public class InnerClass {
		int value = 8;
		// cannot declare static variable in non-static inner class
		//static int val = 1;
		
		public void testMe() {
			System.out.println(value);
			System.out.println(NestedClasses.this.value);
		}
	}
	
	public static void main(String[] args) {
		NestedStaticClass.testMe();
		new NestedClasses().new InnerClass().testMe();
	}
}
