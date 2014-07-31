package pl.praktykiatrem.game.battleship.rules;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

/**
 * Klasa udostępnia funckje, realizujące akcje dostępne w trakcie rozgrywki
 * 
 * @author Filip Różański
 *
 */
public class Game {
	private IRules rules;

	private boolean isGameOver;

	public Game() {
		rules = new CustomRules();
		isGameOver = false;
	}

	public int getBoardSize_H() {
		return rules.getBoardSize_H();
	}

	public int getBoardSize_V() {
		return rules.getBoardSize_V();
	}

	public int getShipsNumber() {
		return rules.getShipsNumber();
	}

	public int[] getShipTypes() {
		return rules.getShipTypes();
	}

	public int makeMove(PlayerStatus p, int x, int y) {
		return rules.makeMove(p, x, y);
	}

	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			Direction direction, int x, int y) {
		return rules.placeShips(p, id, polesNumber, direction, x, y);
	}

	public boolean displaceShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, int x, int y) {
		return rules.displaceShips(player, id, polesNumber, direction, x, y);
	}

	public boolean getIsGameOver() {
		return isGameOver;
	}

	public int getActiveShipsNumber(PlayerStatus player) {
		return rules.getActiveShipsNumber(player);
	}

	public ArrayList<Coordinates> getCoordsList(PlayerStatus player, int id) {
		return player.getCoords(id);
	}

	public Coordinates[] getCoordsTable(PlayerStatus player, int id) {
		return player.getCoordsTable(id);
	}

	public int getAccuracy(PlayerStatus player, boolean hit) {
		return rules.getAccuracy(player, hit);
	}

	public int getShipID(PlayerStatus p, int x, int y) {
		return rules.getShipID(p, x, y);
	}
}
