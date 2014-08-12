/*
 * Plik stworzony dnia 9 lip 2014 przez filiprbla bla
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

import pl.praktykiatrem.game.uniElements.Place;

/**
 * reprezentuje pole na planszy
 * 
 * @author Filip Różański
 *
 */
public class BSPlace extends Place {
	private int shipID = -1;
	private Ship boat;
	private boolean shipOnPlace;

	/**
	 * ustawia wartości charakteryzujące pole na stan początkowy nie ma statku
	 * pole jest w grze
	 */
	public BSPlace() {
		shipOnPlace = false;
		isInGame = true;
	}

	/**
	 * restartuje ustawienia pola
	 */
	@Override
	public void setPlaceClean() {
		shipOnPlace = false;
		isInGame = true;
		shipID = -1;
		boat = null;
	}

	public void setOnPlace(int id) {
		shipOnPlace = true;
		setShipID(id);
	}

	public boolean isShipOnPlace() {
		return shipOnPlace;
	}

	@Override
	public boolean isPlaceInGame() {
		return isInGame;
	}

	public void setShipID(int ID) {
		shipID = ID;
	}

	public int getShipId() {
		return shipID;
	}

	/*
	 * public int getShipID() { return boat.getID(); }
	 */

	/**
	 * wyłącza pole z gry
	 */
	@Override
	public void takeOut() {
		isInGame = false;
	}

	public Ship getShip() {
		return boat;
	}
}
