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
		this.polesNumber = polesNumber;
		this.shipSet = false;
		coords = new ArrayList<Coordinates>(polesNumber);
	}

	/**
	 * zmniejsza liczbe masztów
	 * 
	 * @return jezeli statek zostaje zatopiony zwraca true
	 */
	public int reducePolesNumber() {
		polesNumber--;
		return polesNumber;
	}

	/**
	 *
	 * @return liczba masztów
	 */
	public int getPolesNumber() {
		return polesNumber;
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
	int removeCoordinate(Coordinates c) {
		polesNumber--;
		coords.remove(c);
		return coords.size();
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