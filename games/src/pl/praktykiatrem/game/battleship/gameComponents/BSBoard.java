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
	protected BSPlace[][] gameBoard;

	public BSBoard(int horizontal, int vertical) {
		super(horizontal, vertical);

		gameBoard = new BSPlace[verticalSize][horizontalSize];
		fillGameBoard();
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

	private void fillGameBoard() {
		for (int i = 0; i < verticalSize; i++) {
			for (int j = 0; j < horizontalSize; j++)
				gameBoard[j][i] = new BSPlace();
		}
	}

	public BSPlace[][] getGameBoard() {
		return gameBoard;
	}

	public BSPlace getPlace(int x, int y) {
		return gameBoard[y][x];
	}

	public void clearBoard() {
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++)
				resetPlace(i, j);
	}

	public void resetPlace(int x, int y) {
		gameBoard[y][x].resetPlace();
	}

	public void takeOut(int x, int y) {
		gameBoard[y][x].takeOut();
	}

	public void placeOnBoard(int x, int y, int id) {
		gameBoard[y][x].setOnPlace(id);
	}
}
