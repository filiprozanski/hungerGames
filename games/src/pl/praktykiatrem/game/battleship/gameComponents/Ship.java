package pl.praktykiatrem.game.battleship.gameComponents;

import java.util.ArrayList;

/**
 * reprezentuje statek ustawiany na planszy
 * 
 * @author Filip Rózanski
 *
 */
public class Ship {
    private int shipID;
    private int polesNumber;
    private boolean shipSet;

    /**
     * przechowuje wszystkie koordynaty, na których statek jest umieszczony
     */
    private ArrayList<Coordinates> coords;

    // private Coordinate direction;

    /**
     *
     * @param polesNumber
     *            liczba masztów tworzonego statku
     */
    public Ship(int polesNumber) {
	this.polesNumber = 0;
	this.shipSet = false;
	coords = new ArrayList<Coordinates>(polesNumber);
    }

    /**
     * zmniejsza liczbe masztów
     * 
     * @return jezeli statek zostaje zatopiony zwraca true
     */
    public boolean reducePolesNumber() {
	polesNumber--;
	if (polesNumber == 0)
	    return true;
	else
	    return false;
    }

    /**
     *
     * @return liczba masztów
     */
    public int getPolesNumber() {
	return coords.size();
    }

    /**
     *
     * @return id wybranego statku
     */
    public int getID() {
	return shipID;
    }

    /**
     *
     * @return czy statek jest ustawiony
     */
    public boolean isShipSet() {
	return shipSet;
    }

    /**
     * zmienia status statku na ustawiony
     * 
     */
    public void setShipSet(boolean shipSet) {
	this.shipSet = shipSet;
    }

    /**
     * Dodaje do listy koordynaty
     * 
     * @param c
     *            dodawane koordynaty
     */
    void addCoordinate(Coordinates c) {
	coords.add(c);
	polesNumber++;
    }

    /**
     * Usuwa z listy podane koordynaty
     * 
     * @param c
     */
    boolean removeCoordinate(Coordinates c) {
	polesNumber--;
	return coords.remove(c);
    }

    /**
     * zwraca tablicê aktywnych wspó³rzêdnych statku
     * 
     * @return
     */
    ArrayList<Coordinates> getCoordsTable() {
	return coords;
    }
}