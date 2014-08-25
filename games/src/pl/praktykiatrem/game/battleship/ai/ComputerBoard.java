package pl.praktykiatrem.game.battleship.ai;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ComputerBoard {
	private Game gameRules;
	private int board[][];

	public ComputerBoard(Game gameRules) {
		this.gameRules = gameRules;
		this.board = new int[gameRules.getBoardSizeH()][gameRules
				.getBoardSizeV()];
		for (int i = 0; i < gameRules.getBoardSizeV(); i++)
			for (int j = 0; j < gameRules.getBoardSizeH(); j++)
				board[j][i] = -1;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setMiss(Coordinates coords) {
		board[coords.getY()][coords.getX()] = 0;
	}

	public boolean isBlank(Coordinates coords) {
		if (getBoard(coords) == -1)
			return true;
		else
			return false;
	}

	public boolean isMiss(Coordinates coords) {
		if (getBoard(coords) == 0)
			return true;
		else
			return false;
	}

	public void setHit(Coordinates coords) {
		board[coords.getY()][coords.getX()] = 1;
	}

	public void setSunk(Coordinates coords) {
		board[coords.getY()][coords.getX()] = 2;
	}

	public boolean isHit(Coordinates coords) {
		if (getBoard(coords) == 1)
			return true;
		else
			return false;
	}

	public boolean isSunk(Coordinates coords) {
		if (getBoard(coords) == 2)
			return true;
		else
			return false;
	}

	public int getBoard(Coordinates coords) {
		int x = coords.getX();
		int y = coords.getY();

		if (x >= 0 && x < gameRules.getBoardSizeV() && y >= 0
				&& y < gameRules.getBoardSizeH())
			return board[y][x];
		else
			return 0;
	}

	public void printBoard() {
		for (int i = 0; i < gameRules.getBoardSizeV(); i++) {
			for (int j = 0; j < gameRules.getBoardSizeH(); j++)
				if (board[j][i] == -1)
					System.out.print("+");
				else if ((board[j][i] == 0))
					System.out.print("M");
				else if ((board[j][i] == 1))
					System.out.print("H");
				else if ((board[j][i] == 2))
					System.out.print("S");
				else
					System.out.print(board[j][i]);
			System.out.println();
		}
	}
}
