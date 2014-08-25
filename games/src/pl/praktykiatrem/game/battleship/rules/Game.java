package pl.praktykiatrem.game.battleship.rules;

import java.io.Serializable;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.ai.ComputerBoard;
import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.components.ShootResult;
import pl.praktykiatrem.game.uniElements.enums.Direction;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

/**
 * Klasa udostêpnia funckje, realizuj¹ce akcje dostêpne w trakcie rozgrywki
 * 
 * @author Filip Ró¿añski
 *
 */
public class Game implements Serializable {
	private static final long serialVersionUID = 9219908609794206521L;

	private Rules rules;

	private boolean isGameOver;

	public Game(RulesType rulesType) {
		switch (rulesType) {
		case CUSTOMRULES:
			rules = new CustomRules();
			break;
		case ORIGINALRULES:
			rules = new OriginalRules();
			break;
		default:
			rules = new CustomRules();
			break;
		}
		isGameOver = false;
	}

	public int getBoardSizeH() {
		return rules.getBoardSize_H();
	}

	public int getBoardSizeV() {
		return rules.getBoardSize_V();
	}

	public int getShipsNumber() {
		return rules.getShipsNumber();
	}

	public int[] getShipTypes() {
		return rules.getShipTypes();
	}

	public int getShipType(int id) {
		return rules.getShipTypes(id);
	}

	public ShootResult makeMove(PlayerStatus p, Coordinates coords) {
		return rules.makeMove(p, coords);
	}

	public boolean shipPlacingValidation(ComputerBoard board, int polesNumber,
			Direction dir, Coordinates coords) {
		return rules.shipPlacingValidation(board, polesNumber, dir, coords);
	}

	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			Direction direction, Coordinates coords) {
		return rules.placeShips(p, id, polesNumber, direction, coords);
	}

	public boolean displaceShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, Coordinates coords) {
		return rules.displaceShips(player, id, polesNumber, direction, coords);
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

	public int getShipID(PlayerStatus p, Coordinates coords) {
		return rules.getShipID(p, coords);
	}

	public void resetGame(PlayerStatus player) {
		rules.resetGame(player);
	}

	public GameConstants getConstants() {
		return rules.getConstants();
	}
}
