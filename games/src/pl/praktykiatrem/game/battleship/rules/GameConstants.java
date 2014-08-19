package pl.praktykiatrem.game.battleship.rules;

import java.io.Serializable;

public class GameConstants implements Serializable {
	private static final long serialVersionUID = -3960105237070583994L;
	private final int BOARDSIZE_H;
	private final int BOARDSIZE_V;
	private final int[] SHIPTYPES;

	public GameConstants() {
		BOARDSIZE_H = 10;
		BOARDSIZE_V = 10;
		SHIPTYPES = new int[] { 6, 4, 4, 3, 3, 2, 2 };
	}

	public GameConstants(int V, int H, int[] ships) {
		BOARDSIZE_H = H;
		BOARDSIZE_V = V;
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
