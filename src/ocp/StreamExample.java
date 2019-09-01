package ocp;

import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class StreamExample {
	
	public static void main(String[] args) {
		Double d = Double.valueOf(3.5);
		Stream<Double> s = Stream.of(3.5,3.4,3.3);
		DoubleStream ds = DoubleStream.of(3d,2d);
		System.out.println(d.intValue());
		Consumer<String> c = a -> {System.out.println(a);};
		s.peek(System.out::println).parallel().forEach(System.out::println);
	}
	
}
