/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 * 
 * Copyright ATREM S.A. ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

import java.util.ArrayList;

import pl.praktykiatrem.game.uniElements.Place;
import pl.praktykiatrem.game.uniElements.Player;
import pl.praktykiatrem.game.uniElements.PlayerStatus;

/**
 * Klasa odpowadia za przchowywanie informacji o statusie gracza m.in. liczbie
 * stakow. Przechowuje tez jego plansze, oraz tablice statk�w
 * 
 * @author Filip R�a�ski
 *
 */
public class BSPlayerStatus extends PlayerStatus {
	Ship[] ships;

	private int shipTypes[];

	int shipsNumber;

	private PlayerStats stats;

	private BSBoard board;

	/**
	 * 
	 * @param boardSize_x
	 *            liczba miejsc planszy w poziome
	 * @param boardSize_y
	 *            liczba miejsc planszy w pionie
	 * @param shipsNumber
	 *            liczba statk�w gracza pozosta�a do zbicia na planszy
	 * @param shipTypes
	 *            tablica przechowuj�ca informacje o typie statk�w u�ywanych w
	 *            rozgrywce
	 */
	public BSPlayerStatus(int boardSizeV, int boardSizeH, int[] shipTypes) {
		super();
		this.shipsNumber = 0;
		this.shipTypes = shipTypes;
		board = new BSBoard(boardSizeV, boardSizeH);
		ships = new Ship[shipTypes.length];
		for (int i = 0; i < shipTypes.length; i++)
			setShip(i, shipTypes[i]);
		gamer = new Player();
		stats = new PlayerStats();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BSPlayerStatus))
			return false;

		BSPlayerStatus other2 = (BSPlayerStatus) other;
		if (this.playerID == other2.playerID)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return liczbe statk�w gracza pozosta�ych na planszy
	 */
	public int getShipsNumber() {
		return shipsNumber;
	}

	/**
	 * zwi�ksza liczb� statk�w o jeden
	 */
	public void increaseShipsNumber() {
		shipsNumber++;
	}

	/**
	 * zmmniejsza liczb� statk�w o jeden
	 */
	public void decreaseShipsNumber() {
		if (shipsNumber > 0)
			shipsNumber--;
	}

	public void decreasePolesNumber(int id) {
		ships[id].reducePolesNumber();
	}

	/**
	 * zmniejsza liczb� statk�w po zbiciu o 1
	 */
	public void reduceShipsNumber() {
		shipsNumber--;
	}

	/**
	 * 
	 * @return zwraca plansze gracza
	 */
	public BSBoard getPlansza() {
		return board;
	}

	public Place getPlace(int x, int y) {
		return board.getPlace(x, y);
	}

	/**
	 * 
	 * @param id
	 * @param polesNumber
	 *            liczba maszt�w statku
	 */
	public void setShip(int id, int polesNumber) {
		ships[id] = new Ship(polesNumber);
	}

	/**
	 * 
	 * @param id
	 * @return statek o podanym id
	 */
	public Ship getShip(int id) {
		return ships[id];
	}

	/**
	 * 
	 * @param id
	 * @return statek o podanym id
	 */
	public boolean isShipSet(int id) {
		return ships[id].isShipSet();
	}

	/**
	 * deaktywuje na planszy miejsce opisane parametrami
	 * 
	 * @param x
	 *            wsp�rz�dna pozioma
	 * @param y
	 *            wsp�rz�dna pionowa
	 */
	public void takeOutShip(int x, int y) {
		board.takeOut(x, y);
	}

	/**
	 * 
	 * @param i
	 * @return zwraca liczbe maszt�w statku znajduj�cego si� w tablicy pod
	 *         indeksem i
	 */
	public int getShipTypes(int i) {
		return shipTypes[i];
	}

	/**
	 * zmniejsza liczbe maszt�w o jeden
	 * 
	 * @param x
	 * @param y
	 */
	public int reducePolesNumber(int x, int y) {
		BSPlace p = board.getPlace(x, y);
		int shipID = p.getShipId();
		return ships[shipID].reducePolesNumber();
	}

	public int getShipID(int x, int y) {
		BSPlace p = board.getPlace(x, y);
		return p.getShipId();

	}

	/**
	 * dodaje koordynaty do listy znajduj�cej si� w statku
	 * 
	 * @param ID
	 * @param c
	 */
	public void addCoordinates(int ID, Coordinates c) {
		ships[ID].addCoordinate(c);
	}

	/**
	 * usuwa podane koordynaty z listy znajduj�cej si� w statku
	 * 
	 * @param ID
	 *            numer identzfikaczjnz statku
	 * @param c
	 *            koordynaty do usuni�cia
	 */
	public int removeCoordinate(int ID, Coordinates c) {
		return ships[ID].removeCoordinate(c);
	}

	/**
	 * 
	 * @param ID
	 *            numer identyfikacyjny statku
	 * @return list� koordynat, kt�re zajmuje dany statek
	 */
	public ArrayList<Coordinates> getCoords(int ID) {
		return ships[ID].getCoordsTable();
	}

	/**
	 * 
	 * @param ID
	 *            numer identyfikacyjny statku
	 * @return tablice koordynat, kt�re zajmuje dany statek
	 */
	public Coordinates[] getCoordsTable(int ID) {
		ArrayList<Coordinates> coordsList = ships[ID].getCoordsTable();
		return coordsList.toArray(new Coordinates[coordsList.size()]);
	}

	public int getAccuracy(boolean hit) {
		return stats.getAccuracy(hit);
	}

	public void resetStatus() {
		board.clearBoard();
		for (Ship s : ships)
			s.clearShip();
		shipsNumber = 0;
	}

}
