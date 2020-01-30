package problems.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Conway's Game of Life takes place on an infinite two-dimensional board of square cells.
 * Each cell is either dead or alive, and at each tick, the following rules apply:

Any live cell with less than two live neighbours dies.
Any live cell with two or three live neighbours remains living.
Any live cell with more than three live neighbours dies.
Any dead cell with exactly three live neighbours becomes a live cell.
A cell neighbours another cell if it is horizontally, vertically, or diagonally adjacent.

Implement Conway's Game of Life. It should be able to be initialized with a starting list of
live cell coordinates and the number of steps it should run for. Once initialized, it
should print out the board state at each step. Since it's an infinite board, print out
only the relevant coordinates, i.e. from the top-leftmost live cell to bottom-rightmost live cell.

You can represent a live cell with an asterisk (*) and a dead cell with a dot (.).
 * @author Andrew
 *
 */
public class GameOfLife {
    static final Logger log = LogManager.getLogger(GameOfLife.class);
	public static final int TIMEOUT = 200;
	public static final boolean BENCHMARK = false;
	public static final char ALIVE  = 'o';
	public static final int DEAD    = '-';

	List<List<Boolean>> board = new ArrayList<>();
	int ticks = 0;
	int maxTicks;

	public GameOfLife(boolean[][] initBoard, int maxTicks) {
		for (int i=0;i<initBoard.length;i++) {
			List<Boolean> list = new ArrayList<>();
			for (int j=0;j<initBoard[i].length;j++) {
				list.add(initBoard[i][j]);
			}
			board.add(list);
		}
		this.maxTicks = maxTicks;
	}

	public static void main(String[] args) throws InterruptedException {
		boolean[][] init = { // interesting initial configuration
				{true,false,true},
				{false,true,true},
				{true,false,true},
		};
		boolean[][] glider = {
				{false,true,false},
				{false,false,true},
				{true,true,true},
		};
		boolean[][] exploder = {
				{false,true,false},
				{true,true,true},
				{true,false,true},
				{false,true,false}
		};
		log.debug("Starting new game...");
		Thread.sleep(2000);
		new GameOfLife(glider, 10).start();
		log.debug("Starting new game...");
		Thread.sleep(5000);
		new GameOfLife(exploder, 16).start();
		log.debug("Starting new game...");
		Thread.sleep(5000);
		new GameOfLife(init, 100).start();
	}

	/**
	 * Starts the game!
	 * @throws InterruptedException - if interrupted while waiting to print next board
	 */
	public void start() throws InterruptedException {
		print();
		while (ticks < maxTicks) {
			long then = System.currentTimeMillis();
			tick();
			if (BENCHMARK) {
				log.debug("TIME TO TICK: " + (System.currentTimeMillis() - then));
			}
			Thread.sleep(TIMEOUT);
		}
	}

	/**
	 * Waits for the specified time, then iterates through each position, updating and printing the board:
	 * <ul>
	 *   <li>Any live cell with less than two live neighbours dies.</li>
	 *   <li>Any live cell with two or three live neighbours remains living.</li>
	 *   <li>Any live cell with more than three live neighbours dies.</li>
	 *   <li>Any dead cell with exactly three live neighbours becomes a live cell.</li>
	 * </ul>
	 */
	public void tick() {
		ticks++;
		log.debug("TICK #"+ticks);
		// check if we need to expand the board
		List<Boolean> newTop = new ArrayList<>();
		List<Boolean> newBottom = new ArrayList<>();
		List<Boolean> newLeft= new ArrayList<>();
		List<Boolean> newRight = new ArrayList<>();
		for (int i=1; i<board.size()-1; i++) {
			List<Boolean> row = board.get(i);
			int sizeIdx = row.size()-1;
			boolean firstInRow = row.get(0);
			boolean lastInRow = row.get(sizeIdx);
			if (firstInRow && board.get(i-1).get(0) && board.get(i+1).get(0)) {
				newLeft.add(true);
			} else {
				newLeft.add(false);
			}
			if (lastInRow && board.get(i-1).get(sizeIdx) && board.get(i+1).get(sizeIdx)) {
				newRight.add(true);
			} else {
				newRight.add(false);
			}
		}
		for (int j=1; j<board.get(0).size()-1; j++) {
			int sizeIdx = board.size()-1;
			boolean firstInColumn = board.get(0).get(j);
			boolean lastInColumn = board.get(sizeIdx).get(j);
			if (firstInColumn && board.get(0).get(j-1) && board.get(0).get(j+1)) {
				newTop.add(true);
			} else {
				newTop.add(false);
			}
			if (lastInColumn && board.get(sizeIdx).get(j-1) && board.get(sizeIdx).get(j+1)) {
				newBottom.add(true);
			} else {
				newBottom.add(false);
			}
		}
		// first and last elements of new columns/rows must be dead
		newTop.add(0, Boolean.FALSE);
		newTop.add(Boolean.FALSE);
		newBottom.add(Boolean.FALSE);
		newBottom.add(0, Boolean.FALSE);
		newLeft.add(Boolean.FALSE);
		newLeft.add(0, Boolean.FALSE);
		newRight.add(Boolean.FALSE);
		newRight.add(0, Boolean.FALSE);
		// track living cells as list of integer arrays (idx 0=row, 1=col)
		// don't want to set state here as this will mess up future calculations on the board
		List<Integer[]> liveCells = new ArrayList<>();
		for (int i=0; i<board.size();i++) {
			for (int j=0; j<board.get(i).size();j++) {
				boolean alive = board.get(i).get(j);
				int count = countNeighbors(i,j);
				if (!alive && count == 3) {
					// becomes alive
					liveCells.add(new Integer[] {i,j});
				} else if (alive && (count < 2 || count > 3)) {
					// cell dies
				} else if (alive) {
					// stays alive
					liveCells.add(new Integer[] {i,j});
				}
			}
		}
		// reset board state by killing cells by default
		for (int i=0; i < board.size(); i++) {
			board.get(i).replaceAll((b) -> Boolean.FALSE);
		}
		// set states of living cells
		for (Integer[] point : liveCells) {
			int i = point[0];
			int j = point[1];
			board.get(i).set(j, Boolean.TRUE);
		}
		// merge expanded rows/columns
		if (newTop.contains(Boolean.TRUE)) {
			board.add(0, newTop);
			newLeft.add(0,Boolean.FALSE);
			newRight.add(0,Boolean.FALSE);
		}
		if (newBottom.contains(Boolean.TRUE)) {
			board.add(newBottom);
			newLeft.add(Boolean.FALSE);
			newRight.add(Boolean.FALSE);
		}
		if (newLeft.contains(Boolean.TRUE)) {
			Iterator<Boolean> it = newLeft.iterator();
			for (List<Boolean> row : board) {
				row.add(0, it.next());
			}
		}
		if (newRight.contains(Boolean.TRUE)) {
			Iterator<Boolean> it = newRight.iterator();
			for (List<Boolean> row : board) {
				row.add(it.next());
			}
		}
		print();
	}

	/**
	 * Returns the number of neighbors of cell (i,j) that are alive
	 */
	public int countNeighbors(int i, int j) {
		boolean leftEdge = j == 0;
		boolean topEdge = i == 0;
		boolean botEdge = i == board.size()-1;
		boolean rightEdge = j == board.get(i).size()-1;
		List<Boolean> list = new ArrayList<>();
		if (!leftEdge) list.add(board.get(i).get(j-1));
		if (!rightEdge) list.add(board.get(i).get(j+1));
		if (!topEdge) list.add(board.get(i-1).get(j));
		if (!botEdge) list.add(board.get(i+1).get(j));
		if (!topEdge && !leftEdge) list.add(board.get(i-1).get(j-1));
		if (!topEdge && !rightEdge) list.add(board.get(i-1).get(j+1));
		if (!botEdge && !leftEdge) list.add(board.get(i+1).get(j-1));
		if (!botEdge && !rightEdge) list.add(board.get(i+1).get(j+1));
		list.removeIf(b -> b.equals(Boolean.FALSE));
		return list.size();
	}

	public void print() {
		int rows = board.size();
		for (int i=0; i<rows; i++) {
			int columns = board.get(i).size();
			StringBuilder chars = new StringBuilder();
			for (int j=0; j<columns; j++) {
				char s = board.get(i).get(j) ? ALIVE : DEAD;
				chars.append(s);
			}
			log.debug(chars.toString());
			log.debug("");
		}
	}
}