package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;

public abstract class Rules {

	public Rules() {
		super();
	}

	public abstract boolean makeMove(TTPlayerStatus player, int x, int y);

	public abstract Sign allocateSign();

	public abstract int getBOARDSIZEH();

	public abstract int getBOARDSIZEV();

	public abstract int getBUTTONSIZE();

}