package pl.praktykiatrem.game.tictactoe.rules;

import java.util.ArrayList;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;

public class CustomRules extends Rules {
	private final int BOARDSIZEH = 10;
	private final int BOARDSIZEV = 10;
	private final int SIGNSTOWIN = 5;

	private final int BUTTONSIZE = 40;

	private ArrayList<Sign> availableSigns;
	private TTBoard board;

	public CustomRules() {
		super();
		availableSigns = new ArrayList<Sign>();
		availableSigns.add(Sign.X);
		availableSigns.add(Sign.O);
		board = new TTBoard(BOARDSIZEH, BOARDSIZEV);
	}

	public boolean makeMove(TTPlayerStatus player, int x, int y) {
		Sign playerSign = player.getSign();
		board.setSign(playerSign, x, y);
		int signsInRow = 1;
		int xTemp = x;
		int yTemp = y;

		while (x < (BOARDSIZEH - 1)) {
			x++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					System.out.println("win");
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
					System.out.println("win");
					return true;
				}

				continue;
			}
			break;
		}

		signsInRow = 1;
		x = xTemp;

		while (y < (BOARDSIZEV - 1)) {
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					System.out.println("win");
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
					System.out.println("win");
					return true;
				}

				continue;
			}
			break;
		}

		signsInRow = 1;
		y = yTemp;

		while (y < (BOARDSIZEV - 1) && x < (BOARDSIZEH - 1)) {
			x++;
			y++;
			if (board.getSign(x, y) == playerSign) {
				signsInRow++;
				if (signsInRow == SIGNSTOWIN) {
					System.out.println("win");
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
					System.out.println("win");
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
					System.out.println("win");
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
					System.out.println("win");
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
}
