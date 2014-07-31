package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

/**
 * 
 * Klasa <code>ShootingController</code>
 *
 * Koordynuje fazê strzelania, po¶rednicz±c pomiêdzy prezenterami, które
 * obs³uguj± interfejs graficzny
 *
 * @author filipr
 * @version 30 lip 2014 15:10:51
 *
 */
public class ShootingController {
	/**
	 * obiekt reprezentuj±cy pierwszego z graczy
	 */
	private PlayerStatus player1;
	/**
	 * obiekt reprezentuj±cy drugiego z graczy
	 */
	private PlayerStatus player2;
	/**
	 * obiekt reprezentuj±cy presenter gracza player1
	 */
	private IShootingPresenter pres1;
	/**
	 * obiekt reprezentuj±cy persenter gracza player2
	 */
	private IShootingPresenter pres2;
	/**
	 * obiekt, który udostêpnia akcje wykonywane w grze
	 */
	private Game g;
	/**
	 * liczba statków gracza
	 */
	private int playerShips;
	/**
	 * liczba statków przeciwnika
	 */
	private int enemyShips;
	/**
	 * skuteczno¶æ gracza
	 */
	private int accuracy;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingController</code>
	 *
	 * @param player1
	 * @param player2
	 * @param g
	 */
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

	/**
	 * 
	 * Metoda <code>getView</code>
	 *
	 * @param p
	 * @return obiekt reprezentuj±cy interfejs graficzny
	 */
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

	/**
	 * 
	 * Metoda <code>makeMove</code>
	 * 
	 * koordynuje ruch, wykonuj±c odpowiednie akcje na presenterach
	 * poszczególnych graczy
	 *
	 * @param player
	 *            gracz strzelaj±cy
	 * @param x
	 *            wspó³rzêdna x strza³u
	 * @param y
	 *            wspó³rzêdna y strza³u
	 * @return tre je¶li trafiony, inaczej false
	 */
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

	/**
	 * 
	 * Metoda <code>boardSettingHit</code>
	 * 
	 * wykonuje akcje po trafieniu
	 *
	 * @param shooter
	 * @param victim
	 * @param x
	 * @param y
	 */
	private void boardSettingHit(PlayerStatus shooter, PlayerStatus victim,
			int x, int y) {
		IShootingPresenter sPres = getPresenter(shooter);
		IShootingPresenter vPres = getPresenter(victim);

		vPres.changeIcon(x, y, 8);
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(true);
		sPres.setStats(playerShips, enemyShips, accuracy);
		vPres.setStats(enemyShips, playerShips);
	}

	/**
	 * 
	 * Metoda <code>boardSettingMiss</code>
	 * 
	 * wykonuje akcje po pudle
	 *
	 * @param shooter
	 * @param victim
	 * @param x
	 * @param y
	 */
	private void boardSettingMiss(PlayerStatus shooter, PlayerStatus victim,
			int x, int y) {
		IShootingPresenter sPres = getPresenter(shooter);
		IShootingPresenter vPres = getPresenter(victim);

		vPres.changeIcon(x, y, 9);
		vPres.changeStatus(true);
		sPres.changeStatus(false);
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(false);
		sPres.setStats(playerShips, enemyShips, accuracy);
		vPres.setStats(enemyShips, playerShips);
	}

	/**
	 * 
	 * Metoda <code>getPresenter</code>
	 * 
	 * wi±¿e obiekt gracza z odpowiednim presenterem
	 *
	 * @param player
	 * @return presenter gracza
	 */
	private IShootingPresenter getPresenter(PlayerStatus player) {
		if (player.equals(player1))
			return pres1;
		else if (player.equals(player2))
			return pres2;
		else
			return null;
	}
}
