package pl.praktykiatrem.game.battleship.rules;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.uniElements.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.Direction;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

/**
 * Klasa udostêpnia funckje, realizuj¹ce akcje dostêpne w trakcie rozgrywki
 * 
 * @author Filip Ró¿añski
 *
 */
public class Game {
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

	public int makeMove(BSPlayerStatus p, int x, int y) {
		return rules.makeMove(p, x, y);
	}

	public boolean shipPlacingValidation(ComputerBoard board, int polesNumber,
			Direction dir, int x, int y) {
		return rules.shipPlacingValidation(board, polesNumber, dir, x, y);
	}

	public boolean placeShips(BSPlayerStatus p, int id, int polesNumber,
			Direction direction, int x, int y) {
		return rules.placeShips(p, id, polesNumber, direction, x, y);
	}

	public boolean displaceShips(BSPlayerStatus player, int id,
			int polesNumber, Direction direction, int x, int y) {
		return rules.displaceShips(player, id, polesNumber, direction, x, y);
	}

	public boolean getIsGameOver() {
		return isGameOver;
	}

	public int getActiveShipsNumber(BSPlayerStatus player) {
		return rules.getActiveShipsNumber(player);
	}

	public ArrayList<Coordinates> getCoordsList(BSPlayerStatus player, int id) {
		return player.getCoords(id);
	}

	public Coordinates[] getCoordsTable(BSPlayerStatus player, int id) {
		return player.getCoordsTable(id);
	}

	public int getAccuracy(BSPlayerStatus player, boolean hit) {
		return rules.getAccuracy(player, hit);
	}

	public int getShipID(BSPlayerStatus p, int x, int y) {
		return rules.getShipID(p, x, y);
	}

	public void resetGame(PlayerStatus player) {
		rules.resetGame(player);
	}

	public GameConstants getConstants() {
		return rules.getConstants();
	}
}
