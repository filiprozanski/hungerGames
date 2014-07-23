package pl.praktykiatrem.game.battleship.gameComponents;

public class Ship {
	private int shipID;
	private int polesNumber;

	// private Coordinate direction;

	public Ship(int polesNumber) {
		this.polesNumber = polesNumber;
	}

	public boolean reducePolesNumber() {
		polesNumber--;
		if (polesNumber == 0)
			return true;
		else
			return false;
	}

	public int getPolesNumber() {
		return polesNumber;
	}

	public boolean isShipSunk() {
		if (polesNumber == 0)
			return true;
		else
			return false;
	}

	public void setID(int id) {
		shipID = id;
	}

	public int getID() {
		return shipID;
	}
}
