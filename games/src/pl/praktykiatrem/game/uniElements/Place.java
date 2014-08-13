package pl.praktykiatrem.game.uniElements;

public abstract class Place implements Cloneable {

	protected boolean isInGame;

	public abstract void takeOut();

	public abstract boolean isPlaceInGame();

	public abstract void resetPlace();

	public Place() {
		super();
		isInGame = true;
	}

	public Place(boolean isInGame) {
		this.isInGame = isInGame;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}