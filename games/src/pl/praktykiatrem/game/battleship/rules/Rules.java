package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.gameComponents.BSBoard;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.ShootResult;
import pl.praktykiatrem.game.uniElements.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public abstract class Rules {

	protected final int BOARDSIZE_H = 10;
	protected final int BOARDSIZE_V = 10;
	private final int SHIPTYPES[] = { 6, 4, 4, 3, 3, 2, 2 };
	private final GameConstants constants = new GameConstants(BOARDSIZE_V,
			BOARDSIZE_H, SHIPTYPES);

	public abstract ShootResult makeMove(BSPlayerStatus enemy, int x, int y);

	public abstract boolean displaceShips(BSPlayerStatus player, int id,
			int polesNumber, Direction direction, int x, int y);

	public abstract boolean placeShips(BSPlayerStatus player, int id,
			int polesNumber, Direction direction, int x, int y);

	public abstract boolean shipPlacingValidation(ComputerBoard board,
			int polesNumber, Direction dir, int x, int y);

	public abstract boolean shipDisplacingValidation(BSBoard plansza,
			int polesNumber, Direction dir, int x, int y);

	public abstract boolean shipPlacingValidation(BSBoard plansza,
			int polesNumber, Direction dir, int x, int y);

	public Rules() {
		super();
	}

	public int getShipID(BSPlayerStatus player, int x, int y) {
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

	public int getActiveShipsNumber(BSPlayerStatus player) {
		return player.getShipsNumber();
	}

	public int getAccuracy(BSPlayerStatus player, boolean hit) {
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
