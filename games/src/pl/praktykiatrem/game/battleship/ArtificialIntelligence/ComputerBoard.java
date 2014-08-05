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
				board[j][i] = -2;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setMiss(int x, int y) {
		board[y][x] = -1;
	}

	public void setHit(int x, int y) {
		board[y][x] = 0;
	}

	public void setSink(int id, int x, int y) {
		board[y][x] = id + 1;
	}

	public int getBoard(int x, int y) {
		return board[y][x];
	}

	public void printBoard() {
		for (int i = 0; i < gameRules.getBoardSizeH(); i++) {
			for (int j = 0; j < gameRules.getBoardSizeV(); j++)
				if (board[j][i] == -2)
					System.out.print("+");
				else if ((board[j][i] == -1))
					System.out.print("M");
				else
					System.out.print(board[j][i]);
			System.out.println();
		}
	}
}
