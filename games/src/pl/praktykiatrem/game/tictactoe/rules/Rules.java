package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.graphic.GameState;

public abstract class Rules implements Cloneable {

	public Rules() {
		super();
	}

	public abstract GameState makeMove(TTPlayerStatus player, int x, int y);

	public abstract Sign allocateSign();

	public abstract int getBOARDSIZEH();

	public abstract int getBOARDSIZEV();

	public abstract int getBUTTONSIZE();

	public abstract TTBoard getActualBoard();

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}