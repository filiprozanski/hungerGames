package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.Board;
import pl.praktykiatrem.game.tictactoe.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.GameState;

public abstract class Rules {

	public Rules() {
		super();
	}

	public abstract GameState makeMove(PlayerStatus player, int x, int y);

	public abstract Sign allocateSign();

	public abstract int getBoardSizeH();

	public abstract int getBoardSizeV();

	public abstract int getButtonSize();

	public abstract int getSignsToWin();

	public abstract Board getActualBoard();
}