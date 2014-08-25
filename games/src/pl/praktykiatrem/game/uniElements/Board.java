package pl.praktykiatrem.game.uniElements;

import pl.praktykiatrem.game.battleship.components.Coordinates;

public abstract class Board {

	protected int horizontalSize;
	protected int verticalSize;

	public Board() {
		super();
	}

	public Board(int vertical, int horizontal) { // zamieniona kolejno¶æ
		super();
		horizontalSize = horizontal;
		verticalSize = vertical;
	}

	public int getVerticalSize() {
		return verticalSize;
	}

	public int getHorizontalSize() {
		return horizontalSize;
	}

	public abstract void takeOut(Coordinates coords);

	public abstract void resetPlace(Coordinates coords);

	public abstract void clearBoard();
}