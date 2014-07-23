/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

//import pl.praktykiatrem.game.battleship.gameComponents.Place;

public class PlayerStatus {
	private Board plansza;
	private Ship[] ships;
	private int shipTypes[];
	private int shipsNumber;
	private Player gamer;

	// private String name;

	public PlayerStatus(int boardSize_x, int boardSize_y, int shipsNumber,
			int shipTypes[]) {
		this.shipsNumber = shipsNumber;
		this.shipTypes = shipTypes;
		plansza = new Board(boardSize_x, boardSize_y);
		ships = new Ship[shipsNumber];
		gamer = new Player();
	}

	public String getName() {
		return gamer.getName();
	}

	public void setName(String name) {
		gamer.setName(name);
	}

	public int getShipsNumber() {
		return shipsNumber;
	}

	public Board getPlansza() {
		return plansza;
	}

	public void reduceShipsNumber() {
		shipsNumber--;
	}

	public void setShip(int id, int polesNumber) {
		ships[id] = new Ship(polesNumber);
	}

	public Ship getShip(int id) {
		return ships[id];
	}

	public void takeOutShip(int x, int y) {
		plansza.takeOut(x, y);
	}

	public int getShipTypes(int i) {
		return shipTypes[i];
	}
}
