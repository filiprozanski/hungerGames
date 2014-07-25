package pl.praktykiatrem.game.battleship.gameComponents;

import java.io.Serializable;

public class Ship implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Ship [shipID=" + shipID + ", polesNumber=" + polesNumber + "]";
	}

	private transient int polesNumber;
	private int shipID;

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
