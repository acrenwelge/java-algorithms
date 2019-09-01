package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * This problem was asked by Google.

The area of a circle is defined as πr^2. Estimate π to 3 decimal places using a Monte Carlo method.

Hint: The basic equation of a circle is x2 + y2 = r2.
 * @author Andrew
 *
 */
public class MonteCarloPi {

	/*
	 * Approach:
	 *  - imagine a 1x1 square
	 *  - ratio of inscribed quadrant area to total area is PI*R^2/4=PI*1^2/4=PI/4 (total area is 1)
	 *  - generate points randomly inside the square
	 *  - group the points by whether they are 1 unit away from the origin (inside the quadrant)
	 *  - calculate the ratio of inside to total
	 *  - multiply by 4 to estimate PI
	 */
	public double estimatePi() {
		long t1 = System.currentTimeMillis();
		int numberOfPoints = 10_000_000;
		List<RandomPoint> allPoints = new ArrayList<>(numberOfPoints);
		for (int i=0; i < numberOfPoints; i++) {
			allPoints.add(new RandomPoint());
		}
		List<RandomPoint> inside = allPoints.stream()
		  .filter(p -> Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2)) <= 1)
		  .collect(Collectors.toList());
		double ratio = ((double) inside.size()) / (double) allPoints.size();
		System.out.println("Time to run: " + (System.currentTimeMillis() - t1) + "ms");
		return ratio * 4;
	}
	
	public double estimatePiFaster() {
		long t1 = System.currentTimeMillis();
		int numberOfPoints = 10_000_000;
		List<RandomPoint> outsidePoints = new ArrayList<>(numberOfPoints);
		List<RandomPoint> insidePoints = new ArrayList<>((int) Math.round(numberOfPoints*0.75));
		for (int i=0; i < numberOfPoints; i++) {
			RandomPoint p = new RandomPoint();
			if (Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2)) <= 1) {
				insidePoints.add(p);
			} else {
				outsidePoints.add(new RandomPoint());
			}
		}
		double ratio = ((double) insidePoints.size()) / ((double) outsidePoints.size() + insidePoints.size());
		System.out.println("Time to run: " + (System.currentTimeMillis() - t1) + "ms");
		return ratio * 4;
	}
	
	class RandomPoint {
		double x;
		double y;
		
		public RandomPoint() {
			this.x = Math.random();
			this.y = Math.random();
		}
		
		@Override
		public String toString() {
			return "["+x+","+y+"]";
		}
	}
	
	@Test
	public void testPiToThreeDecimalPrecision() {
		final double PI = 3.1416;
		assertEquals(PI, estimatePi(), 0.001);
		assertEquals(PI, estimatePiFaster(), 0.001);
	}
}
