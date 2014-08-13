package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class Hard implements IComputer {
	private ComputerBoard board;
	private Game game;
	private DensityBoard density;
	private Coordinates coords = new Coordinates(9, 9);
	private int shotCounter = 0;
	private int BoardH;
	private int BoardV;
	private int[] shipTypes;
	private int hitCounter = 0;
	private DensityView densityView;

	public Hard(Game game) {
		this.board = new ComputerBoard(game);
		this.game = game;
		this.density = new DensityBoard(board, game);
		this.BoardH = game.getBoardSizeH();
		this.BoardV = game.getBoardSizeV();
		this.shipTypes = game.getShipTypes();
		this.densityView = new DensityView(BoardV, BoardH);

	}

	@Override
	public Coordinates getCords() {
		if (shotCounter == 0)
			updateDensityBoard();
		shotCounter++;
		return coords;
	}

	private void updateDensityBoard() {
		density.fillDensityBoardWithZeros();
		if (hitCounter == 0)
			fillTrackDensityBoard();
		else
			fillHuntDensityBoard();
		coords = density.getRandomMaxDensityCoords();
		densityView.updateView(density);
	}

	private void fillHuntDensityBoard() {
		for (int i = 0; i < BoardV; i++)
			for (int j = 0; j < BoardH; j++)
				if (board.isHit(i, j)) {
					fillHuntDensityBoardForPoint(i, j);
				}
	}

	private void fillHuntDensityBoardForPoint(int x, int y) {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < BoardV; i++)
				for (int j = 0; j < BoardH; j++) {
					if (game.shipPlacingValidation(board, shipTypes[st],
							Direction.HORIZONTAL, i, j)
							&& shipContainsPoint(x, y, shipTypes[st],
									Direction.HORIZONTAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (game.shipPlacingValidation(board, shipTypes[st],
							Direction.VERTICAL, i, j)
							&& shipContainsPoint(x, y, shipTypes[st],
									Direction.VERTICAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.VERTICAL);
				}
	}

	private void fillTrackDensityBoard() {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < BoardV; i++)
				for (int j = 0; j < BoardH; j++) {
					if (game.shipPlacingValidation(board, shipTypes[st],
							Direction.HORIZONTAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (game.shipPlacingValidation(board, shipTypes[st],
							Direction.VERTICAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.VERTICAL);
				}
	}

	private boolean shipContainsPoint(int point_x, int point_y,
			int polesNumber, Direction dir, int x, int y) {
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (point_x == x && point_y == y + i) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (point_x == x + i && point_y == y) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			board.setSunk(arrayList.get(i).getX(), arrayList.get(i).getY());
		}
		shipTypes[id] = -1;
		hitCounter = hitCounter - arrayList.size() + 1;
		updateDensityBoard();
	}

	@Override
	public void setHit(int x, int y) {
		board.setHit(x, y);
		this.hitCounter++;
		updateDensityBoard();
	}

	public void setMiss(int x, int y) {
		board.setMiss(x, y);
		updateDensityBoard();
	}
}
