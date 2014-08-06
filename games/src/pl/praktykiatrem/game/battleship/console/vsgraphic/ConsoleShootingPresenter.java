package pl.praktykiatrem.game.battleship.console.vsgraphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ConsoleShootingPresenter {
	private Game gameRules;
	private PlayerStatus player;
	private IShootingController controll;
	private boolean gameOver = false;

	public ConsoleShootingPresenter(Game gameRules, PlayerStatus player,
			IShootingController controll) {
		this.gameRules = gameRules;
		this.player = player;
		this.controll = controll;
	}

	private void drawShips() {

	}

	public void shot(int x, int y) {
		if (!gameOver)
			controll.makeMove(player, x, y);
	}

	public void changeStatus(boolean ableToMove) {
	}

	public void gameOver(boolean win) {
		this.gameOver = true;
	}
}
