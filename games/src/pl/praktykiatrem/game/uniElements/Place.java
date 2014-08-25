package pl.praktykiatrem.game.uniElements;

import java.io.Serializable;

import pl.praktykiatrem.game.battleship.components.Coordinates;

public abstract class Place implements Serializable {
	private static final long serialVersionUID = 1496352068486445757L;

	protected boolean isInGame;

	private Coordinates coords;

	public abstract void takeOut();

	public abstract boolean isPlaceInGame();

	public abstract void resetPlace();

	public Place() {
		super();
		isInGame = true;
	}

	public Place(Coordinates coords) {
		this();
		this.coords = coords;
	}

	public Place(boolean isInGame) {
		this.isInGame = isInGame;
	}

	public int getX() {
		return coords.getX();
	}

	public int getY() {
		return coords.getY();
	}

	public Coordinates getCoordinates() {
		return coords;
	}
}