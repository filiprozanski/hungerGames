package pl.praktykiatrem.game.uniElements;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlace;

public class Board {

	/**
	 * tablica dwuwymiarowa[y][x] przechowujaca stany poszczególnych pól C -
	 * puste pole; '0' - '6' - ustawiony statek; M - oddany niecelny strza³; H -
	 * oddany celny strza³
	 */
	protected BSPlace[][] gameBoard;
	protected int horizontalSize;
	protected int verticalSize;

	public Board(int horizontal, int vertical) {
		super();
		horizontalSize = horizontal;
		verticalSize = vertical;

		gameBoard = new BSPlace[verticalSize][horizontalSize];
		fillGameBoard();
	}

	public int getVerticalSize() {
		return verticalSize;
	}

	public int getHorizontalSize() {
		return horizontalSize;
	}

	protected void fillGameBoard() {
		for (int i = 0; i < verticalSize; i++) {
			for (int j = 0; j < horizontalSize; j++)
				gameBoard[j][i] = new BSPlace();
		}
	}

	public Place[][] getGameBoard() {
		return gameBoard;
	}

	public BSPlace getPlace(int x, int y) {
		return gameBoard[y][x];
	}

	public void takeOut(int x, int y) {
		gameBoard[y][x].takeOut();
	}

	public void placeOnBoard(int x, int y, int id) {
		gameBoard[y][x].setOnPlace(id);
	}

	public void resetPlace(int x, int y) {
		gameBoard[y][x].setPlaceClean();
	}

	public void clearBoard() {
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++)
				resetPlace(i, j);
	}

}