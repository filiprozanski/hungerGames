package pl.praktykiatrem.game.tictactoe.rules;

import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;

public class TTGame {
	private Rules rules;

	public TTGame() {
		rules = new CustomRules();
	}

	public boolean makeMove(TTPlayerStatus player, int x, int y) {
		return rules.makeMove(player, x, y);
	}
}
