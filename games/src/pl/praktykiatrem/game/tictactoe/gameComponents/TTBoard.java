package pl.praktykiatrem.game.tictactoe.gameComponents;

import pl.praktykiatrem.game.tictactoe.rules.Sign;
import pl.praktykiatrem.game.uniElements.Board;

public class TTBoard extends Board {
	private TTPlace[][] gameBoard;

	public TTBoard(int horizontal, int vertical) {
		super(horizontal, vertical);
		gameBoard = new TTPlace[horizontal][vertical];
		fillGameBoard();
	}

	private void fillGameBoard() {
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++)
				gameBoard[i][j] = new TTPlace();
	}

	public void setSign(Sign sign, int x, int y) {
		gameBoard[x][y].setOnPlace(sign);
	}

	@Override
	public void takeOut(int x, int y) {
		gameBoard[x][y].takeOut();

	}

	@Override
	public void resetPlace(int x, int y) {
		gameBoard[x][y].resetPlace();

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
}
