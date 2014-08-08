package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public abstract class Rules {

	protected final int BOARDSIZE_H = 10;
	protected final int BOARDSIZE_V = 10;
	private final int SHIPTYPES[] = { 6, 4, 4, 3, 3, 2, 2 };
	private final GameConstants constants = new GameConstants(BOARDSIZE_H,
			BOARDSIZE_V, SHIPTYPES);

	public abstract int makeMove(PlayerStatus enemy, int x, int y);

	public abstract boolean displaceShips(PlayerStatus player, int id,
			int polesNumber, Direction direction, int x, int y);

	public abstract boolean placeShips(PlayerStatus player, int id,
			int polesNumber, Direction direction, int x, int y);

	public abstract boolean shipPlacingValidation(ComputerBoard board,
			int polesNumber, Direction dir, int x, int y);

	public Rules() {
		super();
	}

	public int getShipID(PlayerStatus player, int x, int y) {
		return player.getShipID(x, y);
	}

	public boolean getCurrentPlayer() {
		return false;
	}

	public int getBoardSize_H() {
		return BOARDSIZE_H;
	}

	public int getBoardSize_V() {
		return BOARDSIZE_V;
	}

	public int[] getShipTypes() {
		return SHIPTYPES;
	}

	public int getShipsNumber() {
		return SHIPTYPES.length;
	}

	public int getActiveShipsNumber(PlayerStatus player) {
		return player.getShipsNumber();
	}

	public int getAccuracy(PlayerStatus player, boolean hit) {
		return player.getAccuracy(hit);
	}

	public int getShipTypes(int id) {
		return SHIPTYPES[id];
	}

	public void resetGame(PlayerStatus player) {
		player.resetStatus();
	}

	public GameConstants getConstants() {
		return constants;
	}

}