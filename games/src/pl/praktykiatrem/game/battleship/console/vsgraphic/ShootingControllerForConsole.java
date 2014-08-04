package pl.praktykiatrem.game.battleship.console.vsgraphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ShootingControllerForConsole implements IShootingController {
	private PlayerStatus player1;
	private PlayerStatus player2;
	private IShootingPresenter pres1;
	private ConsoleShootingPresenter pres2;
	private Game g;
	private int playerShips;
	private int enemyShips;
	private int accuracy;
	private StartGraphicForConsole supervisor;

	public ShootingControllerForConsole(PlayerStatus player1,
			PlayerStatus player2, Game g, StartGraphicForConsole supervisor) {
		this.player1 = player1;
		this.player2 = player2;
		this.supervisor = supervisor;
		this.g = g;

		pres1 = new ShootingPresenter(g, player1, this);
		pres2 = new ConsoleShootingPresenter(g, player2, this);

		pres1.setStats(g.getShipsNumber(), g.getShipsNumber());

		pres1.changeStatus(true);
		pres2.changeStatus(false);

	}

	@Override
	public boolean makeMove(PlayerStatus player, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resign(PlayerStatus player) {
		if (player.equals(player1)) {
			pres1.gameOver(false);
			pres2.gameOver(true);
		}

		// drawLeftShips1();
		// drawLeftShips2();
		pres1.changeGiveUpButtonLabel();
	}

	@Override
	public void callMenu() {
		supervisor.callMenu();
	}

	@Override
	public IShootingView getView(int i) {
		return pres1.getView();
	}

}
