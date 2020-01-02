package algorithms.games;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
        static final Logger log = LogManager.getRootLogger();

	// initial game board initialized to all 0's
	public byte[][] board = new byte[][] {
			{0,0,0},
			{0,0,0},
			{0,0,0}};

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		byte currentPlayer = 2;
		byte numPlays = 0;
		while(true) {
			byte winner = game.checkWinner();
			if (winner != -1) {
				log.debug("The winner is Player " + winner);
				break;
			}
			currentPlayer = (byte) (3 - currentPlayer);
			byte[] play;
			if (currentPlayer == 1) {
				play = game.getSmartestMove(currentPlayer);
			} else {
				play = game.getRandomPlay(currentPlayer);
			}
			log.debug("Player " + currentPlayer + " plays move: x="+play[0]+",y="+play[1]);
			game.board[play[0]][play[1]] = currentPlayer;
			numPlays++;
			log.debug("GAME BOARD after play #"+numPlays+":");
			game.printBoard();
		}
		log.debug("WINNING GAME BOARD:");
		game.printBoard();
	}

	public void printBoard() {
		log.debug(String.format(" %d | %d | %d \n",board[0][0],board[0][1],board[0][2]));
		log.debug(String.format(" --------- \n"));
		log.debug(String.format(" %d | %d | %d \n",board[1][0],board[1][1],board[1][2]));
		log.debug(String.format(" --------- \n"));
		log.debug(String.format(" %d | %d | %d \n",board[2][0],board[2][1],board[2][2]));
	}

	/**
	 * Algorithm description:
	 * (1) If you or your opponent has two in a row, play on the remaining square
	 * (2) Otherwise, if there's a move that creates two lines of two in a row, play that.
	 * (3) Otherwise, if the center square is free, play that
	 * (4) Otherwise, if your opponent has played in a corner, play in the opposite corner
	 * (5) Otherwise, if there's an empty corner, play there.
	 * (6) Otherwise, play on any empty square
	 * @param board
	 */
	public byte[] getSmartestMove(byte player) {
		// implement algorithm
		// step 1
		byte[] step1 = winGameOrBlock(player);
		if (step1[0] != -1) return step1;
		// step 2
		// step 3
		if (board[1][1] == 0) return new byte[] {1,1};
		// step 4
		byte[] step4 = playOppositeCorner(player);
		if (step4[0] != -1) return step4;
		// step 5
		byte[] step5 = playCorner();
		if (step5[0] == -1) return step5;
		// step 6
		byte[] step6 = playAnyEmptySquare();
		if (step6[0] != -1) return step6;

		return new byte[] {-1,-1};
	}

	/**
	 * Implements step (6)
	 * If no squares are empty, return [-1,-1]
	 */
	public byte[] playAnyEmptySquare() {
		for (byte x=0; x < 3; x++) {
			for (byte y=0; y < 3; y++) {
				if (board[x][y] == 0)
					return new byte[] {x,y};
			}
		}
		return new byte[] {-1,-1};
	}

	/**
	 * Implements step (5)
	 * Returns the coordinates of any open corner. If none available, return [-1,1]
	 */
	public byte[] playCorner() {
		if (board[0][0] == 0)
			return new byte[] {0,0};
		if (board[2][2] == 0)
			return new byte[] {2,2};
		if (board[0][2] == 0)
			return new byte[] {0,2};
		if (board[2][0] == 0)
			return new byte[] {2,0};
		return new byte[] {-1, -1};
	}

	/**
	 * Implements step (4)
	 * Returns the coordinates of an open corner which is an opposite corner from the opponent.
	 * If none available, return [-1,-1]
	 */
	public byte[] playOppositeCorner(byte player) {
		byte opponent = (byte) (3 - player);
		if (board[0][0] == opponent && board[2][2] == 0) {
			return new byte[] {2,2};
		} else if (board[0][2] == opponent && board[2][0] == 0) {
			return new byte[] {2,0};
		} else if (board[2][2] == opponent && board[0][0] == 0) {
			return new byte[] {0,0};
		} else if (board[2][0] == opponent && board[0][2] == 0) {
			return new byte[] {0,2};
		} else {
			return new byte[] {-1,-1};
		}
	}

	/**
	 * Returns the winner. If no winner exists yet, return -1
	 */
	public byte checkWinner() {
		for (byte player = 1; player < 3; player++) {
			for (byte x=0; x < 3; x++) {
				if (board[x][0] == player && board[x][1] == player && board[x][2] == player) {
					return player;
				}
			}
			for (byte y=0; y < 3; y++) {
				if (board[0][y] == player && board[1][y] == player && board[2][y] == player) {
					return player;
				}
			}
			if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
				return player;
			}
			if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
				return player;
			}
		}
		return -1;
	}

	/**
	 * Returns a board representation after a random play
	 */
	public byte[] getRandomPlay(byte player) {
		byte x;
		byte y;
		while(true) {
			x = (byte) Math.rint((Math.random() * 2));
			y = (byte) Math.rint((Math.random() * 2));
			if (board[x][y] == 0) {
				break;
			}
		}
		return new byte[] {x,y};
	}

	/**
	 * Implement step (1) of the algorithm
	 * @return the coordinates [x,y] where x is the row, y is the column. if no moves exist, return [-1,-1]
	 */
	public byte[] winGameOrBlock(byte player) {
		for (byte x=0; x < 3; x++) {
			if (board[x][0] == player && board[x][1] == player && board[x][2] == 0) {
				return new byte[] {x,2};
			}
		}
		for (byte y=0; y < 3; y++) {
			if (board[0][y] == player && board[1][y] == player && board[2][y] == 0) {
				return new byte[] {2,y};
			}
		}
		if (board[0][0] == player && board[1][1] == player && board[2][2] == 0) {
			return new byte[] {2,2};
		}
		if (board[2][0] == player && board[1][1] == player && board[0][2] == 0) {
			return new byte[] {0,2};
		}
		return new byte[] {-1,-1};
	}

}
