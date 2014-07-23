package pl.praktykiatrem.game.battleship.gameComponents;

/**
 * 
 * Klasa <code>Board</code> reprezentuje plansz� do gry
 *
 * @author filipr
 * @version 11 lip 2014 12:31:46
 *
 */
public class Board {
	/**
	 * tablica dwuwymiarowa[y][x] przechowujaca stany poszczeg�lnych p�l C -
	 * puste pole; '0' - '6' - ustawiony statek; M - oddany niecelny strza�; H -
	 * oddany celny strza�
	 */
	private Place[][] gameBoard;
	private int horizontalSize;
	private int verticalSize;

	public int getVerticalSize() {
		return verticalSize;
	}

	public int getHorizontalSize() {
		return horizontalSize;
	}

	public Board(int horizontal, int vertical) {
		horizontalSize = horizontal;
		verticalSize = vertical;

		gameBoard = new Place[verticalSize][horizontalSize];
		fillGameBoard();
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

	public void placeShip(int x, int y, int id) {
		gameBoard[y][x].setShipOnPlace();
		gameBoard[y][x].setShipID(id);
	}

	public boolean isShipOnPlace(int x, int y) {
		return gameBoard[y][x].isShipOnPlace();
	}

	public boolean isPlaceActive(int x, int y) {
		return gameBoard[y][x].isPlaceInGame();
	}

	public void takeOut(int x, int y) {
		gameBoard[y][x].takeOut();
	}

	public int getShipID(int x, int y) {
		return gameBoard[y][x].getShipID();
	}

	public boolean isShipOnPlaceAndActive(int x, int y) {
		return (isShipOnPlace(x, y) && gameBoard[y][x].isPlaceInGame());
	}

	Place getPlace(int x, int y) {
		return gameBoard[y][x];
	}
}
