package pl.praktykiatrem.game.uniElements;

public abstract class Place {

	protected boolean isInGame;

	public abstract void takeOut();

	public abstract boolean isPlaceInGame();

	public abstract void resetPlace();

	public Place() {
		super();
	}

}