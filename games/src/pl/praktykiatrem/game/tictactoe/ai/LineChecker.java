package pl.praktykiatrem.game.tictactoe.ai;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.tictactoe.gameComponents.Place;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class LineChecker {
	private Sign playerSign;
	private Sign opponentSign;
	private int expectedInLine;

	public LineChecker(Sign playerSign, Sign opponentSign, int expected) {
		this.playerSign = playerSign;
		this.opponentSign = opponentSign;
		expectedInLine = expected;
	}

	public int evaluateCoords(Place[][] board) {
		ArrayList<Coordinates[]> toCheck = getSpacesToCheck(board.length,
				board[0].length);
		int finalScore = 0;
		int score = 0;

		for (Coordinates[] coordsTab : toCheck) {
			for (int i = 0; i < coordsTab.length; i++) {
				int x = coordsTab[i].getX();
				int y = coordsTab[i].getY();

				if (i == 0) {
					if (board[x][y].getSign() == playerSign) {
						score = 1;
					} else if (board[x][y].getSign() == opponentSign) {
						score = -1;
					}
				} else if (i == 1) {
					if (board[x][y].getSign() == playerSign) {
						if (score == 1) {
							score = 10;
						} else if (score == -1) {
							return 0;
						} else {
							score = 1;
						}
					} else if (board[x][y].getSign() == opponentSign) {
						if (score == -1) {
							score = -10;
						} else if (score == 1) {
							return 0;
						} else {
							score = -1;
						}
					}
				} else if (i > 1) {
					if (board[x][y].getSign() == playerSign) {
						if (score > 0) {
							score *= 10;
						} else if (score < 0) {
							return 0;
						} else {
							score = 1;
						}
					} else if (board[x][y].getSign() == opponentSign) {
						if (score < 0) {
							score *= 10;
						} else if (score > 1) {
							return 0;
						} else {
							score = -1;
						}
					}

				}
			}
			finalScore += score;
		}

		return finalScore;
	}

	private ArrayList<Coordinates[]> getSpacesToCheck(int horizontalSize,
			int verticalSize) {
		ArrayList<Coordinates[]> finalTab = new ArrayList<Coordinates[]>();
		ArrayList<Coordinates> tempTab = new ArrayList<Coordinates>();
		for (int i = 0; i < verticalSize; i++)
			for (int j = 0; j + expectedInLine <= horizontalSize; j++) {
				for (int x = 0; x < expectedInLine; x++) {
					tempTab.add(new Coordinates(j + x, i));
				}
				finalTab.add(tempTab.toArray(new Coordinates[expectedInLine]));
			}

		for (int i = 0; i < horizontalSize; i++)
			for (int j = 0; j + expectedInLine <= verticalSize; j++) {
				for (int y = 0; y < expectedInLine; y++) {
					tempTab.add(new Coordinates(i, j + y));
				}
				finalTab.add(tempTab.toArray(new Coordinates[expectedInLine]));
			}

		for (int i = 0; i + expectedInLine <= horizontalSize; i++)
			for (int j = 0; j + expectedInLine <= verticalSize; j++) {
				for (int c = 0; c < expectedInLine; c++) {
					tempTab.add(new Coordinates(i + c, j + c));
				}
				finalTab.add(tempTab.toArray(new Coordinates[expectedInLine]));
			}

		for (int i = horizontalSize - 1; i - expectedInLine >= -1; i--)
			for (int j = 0; j + expectedInLine <= verticalSize; j++) {
				for (int c = 0; c < expectedInLine; c++) {
					tempTab.add(new Coordinates(i - c, j + c));
				}
				finalTab.add(tempTab.toArray(new Coordinates[expectedInLine]));
			}

		return finalTab;

	}
}
