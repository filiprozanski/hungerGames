package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;

public class DensityBoard {
	// private ComputerBoard board;
	private Game game;
	private int[][] density;
	private int BoardH;
	private int BoardV;
	private int[] shipTypes;
	private ComputerBoard board;

	public DensityBoard(ComputerBoard board, Game game) {
		// this.board = board;
		this.game = game;
		this.BoardH = game.getBoardSizeH();
		this.BoardV = game.getBoardSizeV();
		this.density = new int[BoardH][BoardV];
		this.board = board;
		fillDensityBoardWithZeros();
		this.shipTypes = game.getShipTypes();
	}

	public Coordinates getCoords() {
		fillDensityBoardWithZeros();
		trackDensityBoard();
		huntDensityBoard();
		return getMaxDensity();
	}

	private int getDensity(int x, int y) {
		return density[y][x];
	}

	private Coordinates getMaxDensity() {
		int max = 0;
		int x = -1;
		int y = -1;
		for (int i = 0; i < BoardH; i++)
			for (int j = 0; j < BoardV; j++)
				if (getDensity(i, j) > max) {
					max = getDensity(i, j);
					x = i;
					y = j;
				}
		return new Coordinates(x, y);
	}

	private void fillDensityBoardWithZeros() {
		for (int i = 0; i < BoardH; i++)
			for (int j = 0; j < BoardV; j++) {
				density[j][i] = 0;
			}
	}

	private void increacseDensityBoard(int x, int y, int polesNumber,
			Direction dir) {
		for (int i = 0; i < polesNumber; i++) {
			if (dir == Direction.HORIZONTAL) {
				density[y + i][x]++;
			} else {
				density[y][x + i]++;
			}
		}
	}

	private void increacseHuntDensityBoard(int x, int y, int polesNumber,
			Direction dir) {
		for (int i = 0; i < polesNumber; i++) {
			if (dir == Direction.HORIZONTAL) {
				if (board.isBlank(x, y + i))
					density[y + i][x]++;
			} else {
				if (board.isBlank(x + i, y))
					density[y][x + i]++;
			}
		}
	}

	private void huntDensityBoard() {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < BoardH; i++)
				for (int j = 0; j < BoardV; j++) {
					if (shipPlacingValidation(shipTypes[st],
							Direction.HORIZONTAL, i, j))
						increacseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (shipPlacingValidation(shipTypes[st],
							Direction.VERTICAL, i, j))
						increacseDensityBoard(i, j, shipTypes[st],
								Direction.VERTICAL);
				}
	}

	private void trackDensityBoard() {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < BoardH; i++)
				for (int j = 0; j < BoardV; j++) {
					if (shipPlacingValidation(shipTypes[st],
							Direction.HORIZONTAL, i, j))
						increacseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (shipPlacingValidation(shipTypes[st],
							Direction.VERTICAL, i, j))
						increacseDensityBoard(i, j, shipTypes[st],
								Direction.VERTICAL);
				}
	}

	public boolean shipPlacingValidation(int polesNumber, Direction dir, int x,
			int y) {
		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BoardV)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BoardH)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(x, y + i)) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(x + i, y)) {
					return false;
				}
			}
		}
		return true;
	}

	public void printDensityBoard() {
		for (int i = 0; i < BoardH; i++) {
			for (int j = 0; j < BoardV; j++)
				if (getDensity(i, j) == 0)
					System.out.print(" ");
				else if (getDensity(i, j) < 5)
					System.out.print("A");
				else if (getDensity(i, j) < 10)
					System.out.print("B");
				else if (getDensity(i, j) < 15)
					System.out.print("C");
				else if (getDensity(i, j) < 20)
					System.out.print("D");
				else if (getDensity(i, j) < 25)
					System.out.print("E");
				else if (getDensity(i, j) < 30)
					System.out.print("F");
				else if (getDensity(i, j) < 35)
					System.out.print("G");
				else if (getDensity(i, j) < 40)
					System.out.print("H");
				else if (getDensity(i, j) < 45)
					System.out.print("I");
				else if (getDensity(i, j) < 50)
					System.out.print("J");
				else if (getDensity(i, j) < 55)
					System.out.print("K");
				else if (getDensity(i, j) < 60)
					System.out.print("L");
				else if (getDensity(i, j) < 65)
					System.out.print("M");
				else
					System.out.print("X");
			System.out.println();
		}
	}

}
