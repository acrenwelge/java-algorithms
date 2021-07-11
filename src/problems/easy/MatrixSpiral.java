package problems.easy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This problem was asked by Amazon.

Given a N by M matrix of numbers, print out the matrix in a clockwise spiral.

For example, given the following matrix:

[[1,  2,  3,  4,  5],
 [6,  7,  8,  9,  10],
 [11, 12, 13, 14, 15],
 [16, 17, 18, 19, 20]]
You should print out the following:

1
2
3
4
5
10
15
20
19
18
17
16
11
6
7
8
9
14
13
12
 * @author Andrew
 *
 */
public class MatrixSpiral {
	static final Logger log = LogManager.getLogger(MatrixSpiral.class);
	
	int minWidth;
	int maxWidth;
	int minHeight;
	int maxHeight;
	int currHeight;
	int currWidth;
	int[][] matrix;
	
	public static void main(String[] args) {
		new MatrixSpiral(new int[][] {
			{1,  2,  3,  4,  5},
			{6,  7,  8,  9,  10},
			{11, 12, 13, 14, 15},
			{16, 17, 18, 19, 20}
		}).printMatrixSpiral();
		System.out.println("--------");
		new MatrixSpiral(new int[][] {
			{1,  2,  3,  4,  5},
			{6,  7,  8,  9,  10},
		}).printMatrixSpiral();
	}
	
	public MatrixSpiral(int[][] matrix) {
		this.matrix = matrix;
	}
	
	public void printMatrixSpiral() {
		maxHeight = matrix.length - 1;
		maxWidth = matrix[0].length - 1;
		minHeight = 0;
		minWidth = 0;
		currHeight = 0;
		currWidth = 0;
		while (!atEnd()) {
			across();
			if (atEnd()) break;
			down();
			if (atEnd()) break;
			left();
			if (atEnd()) break;
			up();
		}
	}
	
	private void across() {
		for (int x = minWidth; x <= maxWidth; x++) {
			log.debug(matrix[currHeight][x]);
		}
		minHeight++;
		currWidth = maxWidth;
	}
	
	private void down() {
		for (int x = minHeight; x <= maxHeight; x++) {
			log.debug(matrix[x][currWidth]);
		}
		maxWidth--;
		currHeight = maxHeight;
	}
	
	private void up() {
		for (int x = maxHeight; x >= minHeight; x--) {
			log.debug(matrix[x][currWidth]);
		}
		minWidth++;
		currHeight = minHeight;
	}
	
	private void left() {
		for (int x = maxWidth; x >= minWidth; x--) {
			log.debug(matrix[currHeight][x]);
		}
		maxHeight--;
		currWidth = minWidth;
	}
	
	private boolean atEnd() {
		if (minWidth < 100) {
			System.out.println("minWidth:"+minWidth+ ", maxWidth:"+maxWidth);
			System.out.println("minHeight:"+minHeight+ ", maxHeight:"+maxHeight);
		}
		return minWidth >= maxWidth && minHeight >= maxHeight;
	}
}
