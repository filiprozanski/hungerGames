package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;

public class Hard implements IComputer {
	private ComputerBoard board;
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
		this.density = new DensityBoard(board, game);
		this.BoardH = game.getBoardSizeH();
		this.BoardV = game.getBoardSizeV();
		this.shipTypes = game.getShipTypes();
		this.densityView = new DensityView(BoardH, BoardV);

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
		for (int i = 0; i < BoardH; i++)
			for (int j = 0; j < BoardV; j++)
				if (board.isHit(i, j)) {
					fillHuntDensityBoardForPoint(i, j);
				}
	}

	private void fillHuntDensityBoardForPoint(int x, int y) {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < BoardH; i++)
				for (int j = 0; j < BoardV; j++) {
					if (shipPlacingValidation(shipTypes[st],
							Direction.HORIZONTAL, i, j)
							&& shipContainsPoint(x, y, shipTypes[st],
									Direction.HORIZONTAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (shipPlacingValidation(shipTypes[st],
							Direction.VERTICAL, i, j)
							&& shipContainsPoint(x, y, shipTypes[st],
									Direction.VERTICAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.VERTICAL);
				}
	}

	private void fillTrackDensityBoard() {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < BoardH; i++)
				for (int j = 0; j < BoardV; j++) {
					if (shipPlacingValidation(shipTypes[st],
							Direction.HORIZONTAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (shipPlacingValidation(shipTypes[st],
							Direction.VERTICAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.VERTICAL);
				}
	}

	private boolean shipPlacingValidation(int polesNumber, Direction dir,
			int x, int y) {
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
			board.setMiss(arrayList.get(i).getX(), arrayList.get(i).getY());
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
