package pl.praktykiatrem.game.uniElements;

import java.io.Serializable;

public abstract class Place implements Serializable {
	private static final long serialVersionUID = 1496352068486445757L;

	protected boolean isInGame;

	public abstract void takeOut();

	public abstract boolean isPlaceInGame();

	public abstract void resetPlace();

	private int x;

	private int y;

	public Place() {
		super();
		isInGame = true;
	}

	public Place(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}

	public Place(boolean isInGame) {
		this.isInGame = isInGame;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}