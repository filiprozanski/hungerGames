package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import pl.praktykiatrem.game.battleship.rules.Game;

public class ComputerBoard {
	private Game gameRules;
	private int board[][];

	ComputerBoard(Game gameRules) {
		this.gameRules = gameRules;
		this.board = new int[gameRules.getBoardSizeH()][gameRules
				.getBoardSizeV()];
		for (int i = 0; i < gameRules.getBoardSizeH(); i++)
			for (int j = 0; j < gameRules.getBoardSizeV(); j++)
				board[j][i] = -1;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setMiss(int x, int y) {
		board[y][x] = 0;
	}

	public boolean isBlank(int x, int y) {
		if (getBoard(x, y) == -1)
			return true;
		else
			return false;
	}

	public boolean isMiss(int x, int y) {
		if (getBoard(x, y) == 0)
			return true;
		else
			return false;
	}

	public void setHit(int x, int y) {
		board[y][x] = 1;
	}

	public void setSunk(int x, int y) {
		board[y][x] = 2;
	}

	public boolean isHit(int x, int y) {
		if (getBoard(x, y) == 1)
			return true;
		else
			return false;
	}

	public boolean isSunk(int x, int y) {
		if (getBoard(x, y) == 2)
			return true;
		else
			return false;
	}

	public int getBoard(int x, int y) {
		if (x >= 0 && x < gameRules.getBoardSizeH() && y >= 0
				&& y < gameRules.getBoardSizeV())
			return board[y][x];
		else
			return 0;
	}

	public void printBoard() {
		for (int i = 0; i < gameRules.getBoardSizeH(); i++) {
			for (int j = 0; j < gameRules.getBoardSizeV(); j++)
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
