package pl.praktykiatrem.game.tictactoe.gameComponents;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class Board extends pl.praktykiatrem.game.uniElements.Board {
	private Place[][] gameBoard;

	public Board(int horizontal, int vertical) {
		super(horizontal, vertical);
		gameBoard = new Place[horizontal][vertical];
		fillGameBoard();
	}

	public Board(Board b) {
		super(b.getHorizontalSize(), b.getVerticalSize());
		gameBoard = new Place[b.getHorizontalSize()][b.getVerticalSize()];

		Place[][] cBoard = b.getGameBoard();

		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = new Place(cBoard[i][j]);
			}
	}

	private void fillGameBoard() {
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++)
				gameBoard[i][j] = new Place();
	}

	public void setSign(Sign sign, int x, int y) {
		gameBoard[x][y].setOnPlace(sign);
	}

	@Override
	public void takeOut(Coordinates coords) {
		gameBoard[coords.getX()][coords.getY()].takeOut();

	}

	@Override
	public void resetPlace(Coordinates coords) {
		gameBoard[coords.getX()][coords.getY()].resetPlace();

	}

	@Override
	public void clearBoard() {
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++)
				gameBoard[i][j].resetPlace();
	}

	public Sign getSign(int x, int y) {
		return gameBoard[x][y].getSign();
	}

	public Place[][] getGameBoard() {
		return gameBoard;
	}
}
