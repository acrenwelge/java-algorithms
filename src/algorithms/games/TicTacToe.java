package algorithms.games;

/**
 * The Tic-Tac-Toe game board is represented here by a 2D array of bytes,
 * where 0 signifies an empty space on the board, 1 represents Player 1's X,
 * and 2 represents Player 2's O. So one winning board position (for P1) could be:
 * <pre>
 * {{1,0,2},
 *  {0,1,0},
 *  {2,2,1}}
 * </pre>
 * @author Andrew Crenwelge
 */
public class TicTacToe {

	/**
	 * Algorithm description:
	 * - If you or your opponent has two in a row, play on the remaining square
	 * - Otherwise, if there's a move that creates two lines of two in a row, play that.
	 * - Otherwise, if the center square is free, play that
	 * - Otherwise, if your opponent has played in a corner, play in the opposite corner
	 * - Otherwise, if there's an empty corner, play there.
	 * - Otherwise, play on any empty square
	 * @param board
	 */
	public static void playSmartestMove(byte[][] board) {
		// validate the input
		RuntimeException badboard = new IllegalArgumentException("Game board");
		if (board.length != 3) throw badboard;
		for (byte[] b : board) { if (b.length != 3) throw badboard;}
	}

}
