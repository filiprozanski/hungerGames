package pl.praktykiatrem.game.battleship.rules;

public class GameConstants {
	private final int BOARDSIZE_H;
	private final int BOARDSIZE_V;
	private final int[] SHIPTYPES;

	public GameConstants() {
		BOARDSIZE_H = 10;
		BOARDSIZE_V = 10;
		SHIPTYPES = new int[] { 6, 4, 4, 3, 3, 2, 2 };
	}

	public GameConstants(int h, int v, int[] ships) {
		BOARDSIZE_H = h;
		BOARDSIZE_V = v;
		SHIPTYPES = ships;
	}

	public int getBoardSizeH() {
		return BOARDSIZE_H;
	}

	public int getBoardSizeV() {
		return BOARDSIZE_V;
	}

	public int[] getShipTypes() {
		return SHIPTYPES;
	}

	public int getShipType(int id) {
		return SHIPTYPES[id];
	}

	public int getShipsNumber() {
		return SHIPTYPES.length;
	}
}