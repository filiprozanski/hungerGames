package pl.praktykiatrem.game.tictactoe.rules;

import java.util.ArrayList;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;

public class CustomRules extends Rules {
	private final int BOARDSIZEH = 3;
	private final int BOARDSIZEV = 3;
	private ArrayList<Sign> availableSigns;
	private TTBoard board;

	public CustomRules() {
		super();
		availableSigns.add(Sign.X);
		availableSigns.add(Sign.O);
		board = new TTBoard(BOARDSIZEH, BOARDSIZEV);
	}

	public boolean makeMove(TTPlayerStatus player, int x, int y) {

	}

	public int getBOARDSIZEH() {
		return BOARDSIZEH;
	}

	public int getBOARDSIZEV() {
		return BOARDSIZEV;
	}

}
