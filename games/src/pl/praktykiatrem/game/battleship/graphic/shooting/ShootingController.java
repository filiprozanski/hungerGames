package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ShootingController {
	private PlayerStatus player1;

	private PlayerStatus player2;

	private IShootingPresenter pres1;

	private IShootingPresenter pres2;

	private Game g;

	private int a;

	private int b;

	private int c;

	public ShootingController(PlayerStatus player1, PlayerStatus player2, Game g) {
		this.player1 = player1;
		this.player2 = player2;
		this.g = g;

		pres1 = new ShootingPresenter(g, player1, this);
		pres2 = new ShootingPresenter(g, player2, this);

		pres1.setStats(g.getShipsNumber(), g.getShipsNumber());
		pres2.setStats(g.getShipsNumber(), g.getShipsNumber());

		pres1.changeStatus(true);
		pres2.changeStatus(false);
	}

	public IShootingView getView(int p) {
		switch (p) {
		case 1:
			return pres1.getView();
		case 2:
			return pres2.getView();
		default:
			return null;
		}
	}

	public boolean makeMove(PlayerStatus player, int x, int y) {
		if (player.equals(player1)) {
			if (g.makeMove(player2, x, y)) {
				boardSettingHit(player1, player2, x, y);
				return true;
			} else {
				boardSettingMiss(player1, player2, x, y);
				return false;
			}
		} else {
			if (g.makeMove(player1, x, y)) {
				boardSettingHit(player2, player1, x, y);
				return true;
			} else {
				boardSettingMiss(player2, player1, x, y);
				return false;
			}
		}
	}

	private void boardSettingHit(PlayerStatus shooter, PlayerStatus victim,
			int x, int y) {
		IShootingPresenter sPres = getPresenter(shooter);
		IShootingPresenter vPres = getPresenter(victim);

		vPres.changeIcon(x, y, 8);
		a = g.getActiveShipsNumber(shooter);
		b = g.getActiveShipsNumber(victim);
		c = shooter.getAccuracy(true);
		sPres.setStats(a, b, c);
		vPres.setStats(b, a);
	}

	private void boardSettingMiss(PlayerStatus shooter, PlayerStatus victim,
			int x, int y) {
		IShootingPresenter sPres = getPresenter(shooter);
		IShootingPresenter vPres = getPresenter(victim);

		vPres.changeIcon(x, y, 9);
		vPres.changeStatus(true);
		sPres.changeStatus(false);
		a = g.getActiveShipsNumber(shooter);
		b = g.getActiveShipsNumber(victim);
		c = shooter.getAccuracy(false);
		sPres.setStats(a, b, c);
		vPres.setStats(b, a);
	}

	private IShootingPresenter getPresenter(PlayerStatus player) {
		if (player.equals(player1))
			return pres1;
		else if (player.equals(player2))
			return pres2;
		else
			return null;
	}
}
