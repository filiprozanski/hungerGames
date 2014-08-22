package pl.praktykiatrem.game.uniElements;

public abstract class Board {

	protected int horizontalSize;
	protected int verticalSize;

	public Board() {
		super();
	}

	public Board(int vertical, int horizontal) { // zamieniona kolejno��
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

	public abstract void takeOut(int x, int y);

	public abstract void resetPlace(int x, int y);

	public abstract void clearBoard();
}