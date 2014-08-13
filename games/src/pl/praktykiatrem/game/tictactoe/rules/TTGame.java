package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.graphic.GameState;

public class TTGame {
	private Rules rules;

	public TTGame() {
		rules = new CustomRules();
	}

	public GameState makeMove(TTPlayerStatus player, int x, int y) {
		return rules.makeMove(player, x, y);
	}

	public Sign allocateSign() {
		return rules.allocateSign();
	}

	public int getVerticalSize() {
		return rules.getBOARDSIZEV();
	}

	public int getHorizontalSize() {
		return rules.getBOARDSIZEH();
	}

	public int getButtonSize() {
		return rules.getBUTTONSIZE();
	}

	public TTBoard getBoard() {
		return rules.getActualBoard();
	}

	public Rules getRules() {
		return rules;
	}
}
