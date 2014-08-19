/*
 * Plik stworzony dnia 9 lip 2014 przez filiprbla bla
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

import java.io.Serializable;

import pl.praktykiatrem.game.uniElements.Place;

/**
 * reprezentuje pole na planszy
 * 
 * @author Filip Ró¿añski
 *
 */
public class BSPlace extends Place implements Serializable {
	private static final long serialVersionUID = 1126275031769002150L;
	private int shipID = -1;
	private Ship boat;
	private boolean shipOnPlace;

	/**
	 * ustawia wartoœci charakteryzuj¹ce pole na stan pocz¹tkowy nie ma statku
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
	public void resetPlace() {
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
	 * wy³¹cza pole z gry
	 */
	@Override
	public void takeOut() {
		isInGame = false;
	}

	public Ship getShip() {
		return boat;
	}
}
