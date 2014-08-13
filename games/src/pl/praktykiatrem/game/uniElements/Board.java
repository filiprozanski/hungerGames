package pl.praktykiatrem.game.uniElements;

public abstract class Board implements Cloneable {

	protected int horizontalSize;
	protected int verticalSize;

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

	public abstract void takeOut(int x, int y);

	public abstract void resetPlace(int x, int y);

	public abstract void clearBoard();

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}