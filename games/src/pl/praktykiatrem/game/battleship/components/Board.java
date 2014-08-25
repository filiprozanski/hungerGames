package pl.praktykiatrem.game.battleship.components;

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

	public boolean isShipOnPlace(Coordinates coords) {
		return gameBoard[coords.getY()][coords.getX()].isShipOnPlace();
	}

	public boolean isPlaceActive(Coordinates coords) {
		return gameBoard[coords.getY()][coords.getX()].isPlaceInGame();
	}

	public int getShipID(Coordinates coords) {
		return gameBoard[coords.getY()][coords.getX()].getShipId();
	}

	public boolean isShipOnPlaceAndActive(Coordinates coords) {
		return (isShipOnPlace(coords) && gameBoard[coords.getY()][coords.getX()]
				.isPlaceInGame());
	}

	private void fillGameBoard() {
		for (int i = 0; i < verticalSize; i++) {
			for (int j = 0; j < horizontalSize; j++)
				gameBoard[j][i] = new Place(new Coordinates(i, j));
		}
	}

	public Place[][] getGameBoard() {
		return gameBoard;
	}

	public Place getPlace(Coordinates coords) {
		return gameBoard[coords.getY()][coords.getX()];
	}

	@Override
	public void clearBoard() {
		for (int j = 0; j < gameBoard.length; j++)
			for (int i = 0; i < gameBoard[j].length; i++)
				resetPlace(new Coordinates(i, j));
	}

	@Override
	public void resetPlace(Coordinates coords) {
		gameBoard[coords.getY()][coords.getX()].resetPlace();
	}

	@Override
	public void takeOut(Coordinates coords) {
		gameBoard[coords.getY()][coords.getX()].takeOut();
	}

	public void placeOnBoard(Coordinates coords, int id) {
		gameBoard[coords.getY()][coords.getX()].setOnPlace(id);
	}
}
