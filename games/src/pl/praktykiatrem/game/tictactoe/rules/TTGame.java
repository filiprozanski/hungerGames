package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.Board;
import pl.praktykiatrem.game.tictactoe.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.GameState;

public class TTGame {
	private Rules rules;

	public TTGame() {
		rules = new CustomRules();
	}

	public GameState makeMove(PlayerStatus player, int x, int y) {
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

	public Board getBoard() {
		return rules.getActualBoard();
	}

	public Rules getRules() {
		return rules;
	}
}
