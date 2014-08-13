package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.GameState;

public abstract class Rules {

	public Rules() {
		super();
	}

	public abstract GameState makeMove(TTPlayerStatus player, int x, int y);

	public abstract Sign allocateSign();

	public abstract int getBoardSizeH();

	public abstract int getBoardSizeV();

	public abstract int getButtonSize();

	public abstract int getSignsToWin();

	public abstract TTBoard getActualBoard();
}