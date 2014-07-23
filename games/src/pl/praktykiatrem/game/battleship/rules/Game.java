package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public class Game {
	private RulesInterface rules;
	private boolean isGameOver;
	private PlayerStatus currentPlayer;

	public Game() {
		rules = new CustomRules();
		isGameOver = false;
	}

	public boolean makeMove(PlayerStatus p, int x, int y) {
		return rules.makeMove(p, x, y);
	}

	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			char direction, int x, int y) {
		return rules.placeShips(p, id, polesNumber, direction, x, y);
	}

	public boolean getIsGameOver() {
		return isGameOver;
	}

	// TO DO!!!
	private boolean checkIsGameOver() {
		return false;
	}
}
