/*
 * Plik stworzony dnia 9 lip 2014 przez filiprbla bla
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

class Place {
	private Ship boat;
	private boolean shipOnPlace;
	private boolean isInGame;

	public Place() {
		shipOnPlace = false;
		isInGame = true;
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
		boat.setID(ID);
	}

	public int getShipID() {
		return boat.getID();
	}

	public void takeOut() {
		isInGame = false;
	}

	public Ship getShip() {
		return boat;
	}
}
