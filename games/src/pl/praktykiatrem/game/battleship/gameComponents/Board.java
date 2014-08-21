package pl.praktykiatrem.game.battleship.gameComponents;

import java.io.Serializable;

/**
 * 
 * Klasa <code>Board</code> reprezentuje planszê do gry
 *
 * @author hungerGames
 *
 */

public class Board extends pl.praktykiatrem.game.uniElements.Board implements
		Serializable {
	private static final long serialVersionUID = 5263724430201879087L;

	protected Place[][] gameBoard;

	public Board() {
		super();
	}

	public Board(int vertical, int horizontal) {
		super(vertical, horizontal);
		gameBoard = new Place[horizontalSize][verticalSize];
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
				gameBoard[j][i] = new Place();
		}
	}

	public Place[][] getGameBoard() {
		return gameBoard;
	}

	public Place getPlace(int x, int y) {
		return gameBoard[y][x];
	}

	@Override
	public void clearBoard() {
		for (int j = 0; j < gameBoard.length; j++)
			for (int i = 0; i < gameBoard[j].length; i++)
				resetPlace(i, j);
	}

	@Override
	public void resetPlace(int x, int y) {
		gameBoard[y][x].resetPlace();
	}

	@Override
	public void takeOut(int x, int y) {
		gameBoard[y][x].takeOut();
	}

	public void placeOnBoard(int x, int y, int id) {
		gameBoard[y][x].setOnPlace(id);
	}
}
