package pl.praktykiatrem.game.battleship.density;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.ArtificialIntelligence.CoordsList;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class DensityBoard {
	private Game game;
	private int[][] density;
	private int BoardH;
	private int BoardV;
	private ComputerBoard board;
	private CoordsList list;

	Coordinates coords = new Coordinates(0, 0);

	public DensityBoard(ComputerBoard board, Game game) {
		this.game = game;
		this.BoardH = game.getBoardSizeH();
		this.BoardV = game.getBoardSizeV();
		this.density = new int[BoardH][BoardV];
		this.board = board;
		this.list = new CoordsList(board, game);
		fillDensityBoardWithZeros();
	}

	public int getDensity(int x, int y) {
		return density[y][x];
	}

	public Coordinates getRandomMaxDensityCoords() {
		getMaxDensityCoords();
		coords = list.getCoords(Rand.getRand(list.getListSize()));
		return coords;
	}

	private void getMaxDensityCoords() {
		list.clear();
		for (int i = 0; i < BoardV; i++)
			for (int j = 0; j < BoardH; j++) {
				if (getDensity(i, j) == getMaxDensity()) {
					list.addToList(i, j);
				}
			}
	}

	public int getMaxDensity() {
		int max = 0;
		for (int i = 0; i < BoardV; i++)
			for (int j = 0; j < BoardH; j++)
				if (getDensity(i, j) > max) {
					max = getDensity(i, j);
				}
		return max;
	}

	public void fillDensityBoardWithZeros() {
		for (int i = 0; i < BoardV; i++)
			for (int j = 0; j < BoardH; j++) {
				density[j][i] = 0;
			}
	}

	public void increaseDensityBoard(int x, int y, int polesNumber,
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

	public void printBoard() {
		for (int i = 0; i < game.getBoardSizeV(); i++) {
			for (int j = 0; j < game.getBoardSizeH(); j++)
				System.out.print(getDensity(i, j));
			System.out.println();
		}
	}

}
