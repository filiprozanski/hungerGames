/*
 * Plik stworzony dnia 9 lip 2014 przez filiprbla bla
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

/**
 * reprezentuje pole na planszy
 * 
 * @author Filip Ró¿añski
 *
 */
class Place {
    private int shipID;
    private Ship boat;
    private boolean shipOnPlace;
    private boolean isInGame;

    /**
     * ustawia wartoœci charakteryzuj¹ce pole na stan pocz¹tkowy nie ma statku
     * pole jest w grze
     */
    public Place() {
	shipOnPlace = false;
	isInGame = true;
    }

    /**
     * restartuje ustawienia pola
     */
    public void setPlaceClean() {
	shipOnPlace = false;
	isInGame = true;
	shipID = -1;
	boat = null;
    }

    public void setShipOnPlace(int id) {
	shipOnPlace = true;
	shipID = id;
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

    /*
     * public int getShipID() { return boat.getID(); }
     */

    /**
     * wy³¹cza pole z gry
     */
    public void takeOut() {
	isInGame = false;
    }

    public Ship getShip() {
	return boat;
    }
}
