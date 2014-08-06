package pl.praktykiatrem.game.battleship.console.vsgraphic;

import java.awt.Component;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingView;
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
	private JFrame f;

	public ShootingControllerForConsole(PlayerStatus player1,
			PlayerStatus player2, Game g, StartGraphicForConsole supervisor) {
		this.player1 = player1;
		this.player2 = player2;
		this.supervisor = supervisor;
		this.g = g;
		f = new JFrame("Co za badziew...");

		pres1 = new ShootingPresenter(g, player1, this);
		f.getContentPane().add((Component) pres1.getView());
		f.setSize(660, 450);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);

		pres2 = new ConsoleShootingPresenter(g, player2, this);

		pres1.setStats(g.getShipsNumber(), g.getShipsNumber());

		pres1.changeStatus(true);
		pres2.changeStatus(false);

	}

	@Override
	public boolean makeMove(PlayerStatus player, int x, int y) {
		if (player.equals(player1)) {
			int result = g.makeMove(player2, x, y);
			if (result >= 1) {
				boardSettingHit(player1, player2, x, y);
				if (result == 2) {
					int id = g.getShipID(player2, x, y);
					pres1.drawShip(g.getCoordsTable(player2, id));
					if (player2.getShipsNumber() == 0) {
						// gameOver(player1);
					}
				}
				return true;
			} else {
				// boardSettingMiss(player1, player2, x, y);
				return false;
			}
		} else {
			int result = g.makeMove(player1, x, y);
			if (result >= 1) {
				boardSettingHit(player2, player1, x, y);
				if (result == 2) {
					int id = g.getShipID(player1, x, y);
					// pres2.drawShip(g.getCoordsTable(player1, id));
					if (player1.getShipsNumber() == 0) {
						// gameOver(player2);
					}
				}
				return true;
			} else {
				// boardSettingMiss(player2, player1, x, y);
				return false;
			}
		}
	}

	private void boardSettingHit(PlayerStatus shooter, PlayerStatus victim,
			int x, int y) {
		IShootingPresenter sPres = getPresenter(shooter);
		// ConsoleShootingPresenter vPres = getPresenter(victim);

		// vPres.changeStateIcon(x, y, 0);
		sPres.changeBattlePlaceIcon(x, y, 2);
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(true);
		sPres.setStats(playerShips, enemyShips, accuracy);
		// vPres.setStats(enemyShips, playerShips);
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

	private IShootingPresenter getPresenter(PlayerStatus player) {
		if (player.equals(player1))
			return pres1;
		// else if (player.equals(player2))
		// return pres2;
		else
			return null;
	}
}
