package pl.praktykiatrem.game.tictactoe.rules;

import java.util.ArrayList;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.graphic.GameState;

public class CustomRules extends Rules implements Cloneable {
	private final int BOARDSIZEH = 3;
	private final int BOARDSIZEV = 3;
	private final int SIGNSTOWIN = 3;
	private final int BUTTONSIZE = 100; // powinno byæ w sumie wiêcej ni¿ 300

	private ArrayList<Sign> availableSigns;
	private TTBoard board;
	private int moves;

	public CustomRules() {
		super();
		moves = 0;
		availableSigns = new ArrayList<Sign>();
		availableSigns.add(Sign.X);
		availableSigns.add(Sign.O);
		board = new TTBoard(BOARDSIZEH, BOARDSIZEV);
	}

	public CustomRules(CustomRules r) {
		super();
		this.availableSigns = r.getAvailableSigns();
		this.moves = r.getMovesNumber();
		this.board = new TTBoard(r.getActualBoard());
	}

	public GameState makeMove(TTPlayerStatus player, int x, int y) {
		Sign playerSign = player.getSign();
		board.setSign(playerSign, x, y);
		board.takeOut(x, y);
		moves++;

		if (checkHorizontal(x, y, playerSign)
				|| checkVertical(x, y, playerSign)
				|| checkDiagonal(x, y, playerSign))
			return GameState.WINNER;

		if (moves == BOARDSIZEH * BOARDSIZEV)
			return GameState.DRAW;

		return GameState.GAME;
	}

	private boolean checkHorizontal(int x, int y, Sign playerSign) {
		int signsInRow = 1;
		int xTemp = x;
		int yTemp = y;

		while (x < (BOARDSIZEH - 1)) {
			x++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}
				continue;
			}
			break;
		}

		x = xTemp;

		while (x > 0) {
			x--;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}

				continue;
			}
			break;
		}

		return false;
	}

	private boolean checkVertical(int x, int y, Sign playerSign) {
		int signsInRow = 1;
		int xTemp = x;
		int yTemp = y;

		while (y < (BOARDSIZEV - 1)) {
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}
				continue;
			}
			break;
		}

		y = yTemp;

		while (y > 0) {
			y--;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}

				continue;
			}
			break;
		}

		return false;
	}

	private boolean checkDiagonal(int x, int y, Sign playerSign) {
		int signsInRow = 1;
		int xTemp = x;
		int yTemp = y;

		while (y < (BOARDSIZEV - 1) && x < (BOARDSIZEH - 1)) {
			x++;
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}

				continue;
			}
			break;
		}

		y = yTemp;
		x = xTemp;

		while (y > 0 && x > 0) {
			x--;
			y--;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}

				continue;
			}
			break;
		}

		signsInRow = 1;
		y = yTemp;
		x = xTemp;

		while (y < (BOARDSIZEV - 1) && x > 0) {
			x--;
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}

				continue;
			}
			break;
		}

		y = yTemp;
		x = xTemp;

		while (y > 0 && x < (BOARDSIZEH - 1)) {
			x++;
			y--;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					return true;
				}

				continue;
			}
			break;
		}

		return false;
	}

	public int getBOARDSIZEH() {
		return BOARDSIZEH;
	}

	public int getBOARDSIZEV() {
		return BOARDSIZEV;
	}

	public Sign allocateSign() {
		return availableSigns.remove(0);
	}

	public int getBUTTONSIZE() {
		return BUTTONSIZE;
	}

	public TTBoard getActualBoard() {
		return board;
	}

	private ArrayList<Sign> getAvailableSigns() {
		return availableSigns;
	}

	private int getMovesNumber() {
		return moves;
	}

	@Override
	public Object clone() {
		return super.clone();
	}
}
