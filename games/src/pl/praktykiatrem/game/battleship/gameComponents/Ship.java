package pl.praktykiatrem.game.battleship.gameComponents;

import java.util.ArrayList;

/**
 * reprezentuje statek ustawiany na planszy
 * 
 * @author Filip R�zanski
 *
 */
public class Ship {
	private int shipID;

	private int polesNumber;

	private boolean shipSet;

	private Direction dir;

	/**
	 * przechowuje wszystkie koordynaty, na kt�rych statek jest umieszczony
	 */
	private ArrayList<Coordinates> coords;

	// private Coordinate direction;

	/**
	 *
	 * @param polesNumber
	 *            liczba maszt�w tworzonego statku
	 */
	public Ship(int polesNumber) {
		this.polesNumber = 0;
		this.shipSet = false;
		coords = new ArrayList<Coordinates>(polesNumber);
	}

	/**
	 * zmniejsza liczbe maszt�w
	 * 
	 * @return jezeli statek zostaje zatopiony zwraca true
	 */
	public int reducePolesNumber() {
		polesNumber--;
		return polesNumber;
	}

	/**
	 *
	 * @return liczba maszt�w
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
	 * zwraca tablic� aktywnych wsp�rz�dnych statku
	 * 
	 * @return
	 */
	ArrayList<Coordinates> getCoordsTable() {
		return coords;
	}

	public Coordinates getInitialCoords() {
		return coords.get(0);
	}

	void clearShip() {
		shipSet = false;
		polesNumber = 0;
		coords.clear();
	}

	public Direction getDirection() {
		return dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

}