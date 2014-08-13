package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.GameState;

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
		return rules.getBoardSizeV();
	}

	public int getHorizontalSize() {
		return rules.getBoardSizeH();
	}

	public int getButtonSize() {
		return rules.getButtonSize();
	}

	public TTBoard getBoard() {
		return rules.getActualBoard();
	}

	public Rules getRules() {
		return rules;
	}
}
