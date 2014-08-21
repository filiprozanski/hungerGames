package pl.praktykiatrem.game.tictactoe.rules;

import java.util.ArrayList;

import pl.praktykiatrem.game.tictactoe.gameComponents.Board;
import pl.praktykiatrem.game.tictactoe.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.GameState;

public class CustomRules extends Rules implements Cloneable {
	private final int BOARD_SIZE_H = 3;
	private final int BOARD_SIZE_V = 3;
	private final int SIGNS_TO_WIN = 3;
	private final int BUTTON_SIZE = 100; // powinno byæ w sumie wiêcej ni¿ 300

	private ArrayList<Sign> availableSigns;
	private Board board;
	private int moves;

	public CustomRules() {
		super();
		moves = 0;
		availableSigns = new ArrayList<Sign>();
		availableSigns.add(Sign.X);
		availableSigns.add(Sign.O);
		board = new Board(BOARD_SIZE_H, BOARD_SIZE_V);
	}

	public CustomRules(CustomRules r) {
		super();
		this.availableSigns = r.getAvailableSigns();
		this.moves = r.getMovesNumber();
		this.board = new Board(r.getActualBoard());
	}

	public GameState makeMove(PlayerStatus player, int x, int y) {
		Sign playerSign = player.getSign();
		board.setSign(playerSign, x, y);
		board.takeOut(x, y);
		moves++;

		if (checkHorizontal(x, y, playerSign)
				|| checkVertical(x, y, playerSign)
				|| checkDiagonal(x, y, playerSign))
			return GameState.WINNER;

		if (moves == BOARD_SIZE_H * BOARD_SIZE_V)
			return GameState.DRAW;

		return GameState.GAME;
	}

	private boolean checkHorizontal(int x, int y, Sign playerSign) {
		int signsInRow = 1;
		int xTemp = x;
		int yTemp = y;

		while (x < (BOARD_SIZE_H - 1)) {
			x++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNS_TO_WIN) {
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
				if (signsInRow == SIGNS_TO_WIN) {
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

		while (y < (BOARD_SIZE_V - 1)) {
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNS_TO_WIN) {
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
				if (signsInRow == SIGNS_TO_WIN) {
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

		while (y < (BOARD_SIZE_V - 1) && x < (BOARD_SIZE_H - 1)) {
			x++;
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNS_TO_WIN) {
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
				if (signsInRow == SIGNS_TO_WIN) {
					return true;
				}

				continue;
			}
			break;
		}

		signsInRow = 1;
		y = yTemp;
		x = xTemp;

		while (y < (BOARD_SIZE_V - 1) && x > 0) {
			x--;
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNS_TO_WIN) {
					return true;
				}

				continue;
			}
			break;
		}

		y = yTemp;
		x = xTemp;

		while (y > 0 && x < (BOARD_SIZE_H - 1)) {
			x++;
			y--;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNS_TO_WIN) {
					return true;
				}

				continue;
			}
			break;
		}

		return false;
	}

	public int getBoardSizeH() {
		return BOARD_SIZE_H;
	}

	public int getBoardSizeV() {
		return BOARD_SIZE_V;
	}

	public Sign allocateSign() {
		return availableSigns.remove(0);
	}

	public int getButtonSize() {
		return BUTTON_SIZE;
	}

	public int getSignsToWin() {
		return SIGNS_TO_WIN;
	}

	public Board getActualBoard() {
		return board;
	}

	private ArrayList<Sign> getAvailableSigns() {
		return availableSigns;
	}

	private int getMovesNumber() {
		return moves;
	}
}
