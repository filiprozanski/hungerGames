package pl.praktykiatrem.game.battleship.density;

import pl.praktykiatrem.game.battleship.ai.ComputerBoard;
import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class DensityController {
	private DensityBoard density;
	private DensityView densityView;
	private Game game;
	private int[] shipTypes;

	public DensityController(Game g, ComputerBoard board) {
		this.game = g;
		this.shipTypes = game.getShipTypes();
		this.density = new DensityBoard(board, game);
		this.densityView = new DensityView(game.getBoardSizeV(),
				game.getBoardSizeH());
	}

	public void showHint(boolean status) {
		densityView.showHint(status);
	}

	public Coordinates getCords(ComputerBoard computerBoard) {
		return density.getRandomMaxDensityCoords();
	}

	public void updateDensityView() {
		densityView.updateView(density);
	}

	public void updateDensityBoard(int hitCounter, ComputerBoard computerBoard) {
		density.fillDensityBoardWithZeros();
		if (hitCounter == 0)
			fillTrackDensityBoard(computerBoard);
		else
			fillHuntDensityBoard(computerBoard);
		densityView.updateView(density);
	}

	private void fillTrackDensityBoard(ComputerBoard computerBoard) {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < game.getBoardSizeV(); i++)
				for (int j = 0; j < game.getBoardSizeH(); j++) {
					if (game.shipPlacingValidation(computerBoard,
							shipTypes[st], Direction.HORIZONTAL,
							new Coordinates(i, j)))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (game.shipPlacingValidation(computerBoard,
							shipTypes[st], Direction.VERTICAL, new Coordinates(
									i, j)))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.VERTICAL);
				}
	}

	private void fillHuntDensityBoard(ComputerBoard computerBoard) {
		for (int i = 0; i < game.getBoardSizeV(); i++)
			for (int j = 0; j < game.getBoardSizeH(); j++)
				if (computerBoard.isHit(new Coordinates(i, j))) {
					fillHuntDensityBoardForPoint(i, j, computerBoard);
				}
	}

	private void fillHuntDensityBoardForPoint(int x, int y,
			ComputerBoard computerBoard) {
		for (int st = 0; st < shipTypes.length; st++)
			for (int i = 0; i < game.getBoardSizeV(); i++)
				for (int j = 0; j < game.getBoardSizeH(); j++) {
					if (game.shipPlacingValidation(computerBoard,
							shipTypes[st], Direction.HORIZONTAL,
							new Coordinates(i, j))
							&& shipContainsPoint(x, y, shipTypes[st],
									Direction.HORIZONTAL, i, j))
						density.increaseDensityBoard(i, j, shipTypes[st],
								Direction.HORIZONTAL);
					if (game.shipPlacingValidation(computerBoard,
							shipTypes[st], Direction.VERTICAL, new Coordinates(
									i, j))
							&& shipContainsPoint(x, y, shipTypes[st],
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
}
