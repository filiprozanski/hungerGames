/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

import java.util.ArrayList;

/**
 * Klasa odpowadia za przchowywanie informacji o statusie gracza m.in. liczbie
 * stakow. Przechowuje tez jego plansze, oraz tablice statk�w
 * 
 * @author Filip R�a�ski
 *
 */
public class PlayerStatus {
	private Board plansza;
	private Ship[] ships;
	private int shipTypes[];
	private int shipsNumber;
	private Player gamer;

	// private String name;

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
	public PlayerStatus(int boardSizeH, int boardSizeV, int shipTypes[]) {
		this.shipsNumber = shipTypes.length;
		this.shipTypes = shipTypes;
		plansza = new Board(boardSizeH, boardSizeV);
		ships = new Ship[shipsNumber];
		for (int i = 0; i < shipsNumber; i++)
			setShip(i, shipTypes[i]);
		gamer = new Player();
	}

	/**
	 * 
	 * @return imi� gracza
	 */
	public String getName() {
		return gamer.getName();
	}

	/**
	 * Ustawia imi� gracza
	 * 
	 * @param name
	 */
	public void setName(String name) {
		gamer.setName(name);
	}

	/**
	 * 
	 * @return liczbe statk�w gracza pozosta�ych na planszy
	 */
	public int getShipsNumber() {
		return shipsNumber;
	}

	/**
	 * 
	 * @return zwraca plansze gracza
	 */
	public Board getPlansza() {
		return plansza;
	}

	/**
	 * zmniejsza liczb� statk�w po zbiciu o 1
	 */
	public void reduceShipsNumber() {
		shipsNumber--;
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
		plansza.takeOut(x, y);
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
	public boolean reducePolesNumber(int x, int y) {
		return plansza.getPlace(x, y).getShip().reducePolesNumber();
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

	public void removeCoordinate(int ID, Coordinates c) {
		ships[ID].removeCoordinate(c);
	}

	public ArrayList<Coordinates> getCoordsTable(int ID) {
		return ships[ID].getCoordsTable();
	}
}
