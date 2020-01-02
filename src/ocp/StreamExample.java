package ocp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
        static final Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		generalStreams();
		primitiveStreams();
	}

	public static void generalStreams() {
		Stream<String> s = Stream.of("a","b","c");
		s.limit(2).skip(1).map(str -> str.concat("d")).forEach(System.out::println);
	}

	public static void primitiveStreams() {
		IntStream is = IntStream.range(0, 10);
		is.findAny();
		int isum = is.filter(i -> i % 2 == 0).sum();
        DoubleStream ds = DoubleStream.of(0,1,2,3,4,5);
        double dsum = ds.sum();
        log.debug(isum + dsum);
        Stream<Integer> ints = Stream.of(1,4,2);
        UnaryOperator<Double> dop;
	}

}
