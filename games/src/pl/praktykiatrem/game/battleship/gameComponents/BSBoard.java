package pl.praktykiatrem.game.battleship.gameComponents;

import pl.praktykiatrem.game.uniElements.Board;

/**
 * 
 * Klasa <code>BSBoard</code> reprezentuje planszê do gry
 *
 * @author hungerGames
 *
 */

public class BSBoard extends Board {
	public BSBoard(int horizontal, int vertical) {
		super(horizontal, vertical);
	}

	public boolean isShipOnPlace(int x, int y) {
		return gameBoard[y][x].isShipOnPlace();
	}

	public boolean isPlaceActive(int x, int y) {
		return gameBoard[y][x].isPlaceInGame();
	}

	public int getShipID(int x, int y) {
		return gameBoard[y][x].getShipId();
	}

	public boolean isShipOnPlaceAndActive(int x, int y) {
		return (isShipOnPlace(x, y) && gameBoard[y][x].isPlaceInGame());
	}
}
