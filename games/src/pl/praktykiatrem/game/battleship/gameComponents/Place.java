/*
 * Plik stworzony dnia 9 lip 2014 przez filiprbla bla
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

class Place {
	private int shipID;
	private boolean shipOnPlace;
	private boolean isInGame;

	public Place() {
		shipOnPlace = false;
		isInGame = true;
	}

	public void setPlaceClean() {
		shipOnPlace = false;
		isInGame = true;
		shipID = -1;
	}

	public void setShipOnPlace() {
		shipOnPlace = true;
	}

	public boolean isShipOnPlace() {
		return shipOnPlace;
	}

	public boolean isPlaceInGame() {
		return isInGame;
	}

	public void setShipID(int ID) {
		shipID = ID;
	}

	public int getShipId() {
		return shipID;
	}

	public void takeOut() {
		isInGame = false;
	}
}
