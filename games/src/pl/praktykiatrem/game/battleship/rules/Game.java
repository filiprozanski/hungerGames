package pl.praktykiatrem.game.battleship.rules;

import java.io.Serializable;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.ShootResult;
import pl.praktykiatrem.game.uniElements.PlayerStatus;
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

	public ShootResult makeMove(PlayerStatus p, int x, int y) {
		return rules.makeMove(p, x, y);
	}

	public boolean shipPlacingValidation(ComputerBoard board, int polesNumber,
			Direction dir, int x, int y) {
		return rules.shipPlacingValidation(board, polesNumber, dir, x, y);
	}

	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			Direction direction, int x, int y) {
		return rules.placeShips(p, id, polesNumber, direction, x, y);
	}

	public boolean displaceShips(PlayerStatus player, int id,
			int polesNumber, Direction direction, int x, int y) {
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

	public void resetGame(PlayerStatus player) {
		rules.resetGame(player);
	}

	public GameConstants getConstants() {
		return rules.getConstants();
	}
}
