package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.Place;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.ShootResult;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
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
public class ShootingControllerOnline implements IShootingController {
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
	private IShootingPresenterControll pres1;
	/**
	 * obiekt reprezentuj±cy persenter gracza player2
	 */
	private IShootingPresenterControll pres2;
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

	private RMIServer supervisor;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingController</code>
	 *
	 * @param player1
	 * @param player2
	 * @param g
	 */
	public ShootingControllerOnline(PlayerStatus player1, PlayerStatus player2,
			Game g, RMIServer supervisor) {
		this.player1 = player1;
		this.player2 = player2;
		this.supervisor = supervisor;
		this.g = g;
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
	 * @return true je¶li trafiony, inaczej false
	 * @throws RemoteException
	 */
	@Override
	public boolean makeMove(PlayerStatus player, int x, int y)
			throws RemoteException {
		if (player.equals(player1)) {
			return makeMove(player1, player2, x, y);
		} else {
			return makeMove(player2, player1, x, y);
		}
	}

	private boolean makeMove(PlayerStatus shooter, PlayerStatus victim, int x,
			int y) throws RemoteException {
		ShootResult result = g.makeMove(victim, x, y);
		switch (result) {
		case HIT:
			boardSettingHit(shooter, victim, x, y);
			return true;
		case SINK:
			int id = g.getShipID(victim, x, y);
			boardSettingSink(shooter, victim, x, y, id);
			if (victim.getShipsNumber() == 0) {
				gameOver(shooter);
			}
			return true;
		case MISS:
			boardSettingMiss(shooter, victim, x, y);
			return false;
		default:
			return false;
		}
	}

	private void drawLeftShips(PlayerStatus opponent) {

		IShootingPresenterControll playerPres = getPresenter(getOpposePlayer(opponent));

		for (int j = 0; j < g.getBoardSizeH(); j++)
			for (int i = 0; i < g.getBoardSizeV(); i++) {
				Place place = opponent.getPlace(i, j);
				if (place.isShipOnPlace()
						&& opponent.getPlace(i, j).isPlaceInGame())
					playerPres.fchangeIcon(i, j, place.getShipId() + 1);

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
			int x, int y) throws RemoteException {
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(true);
		supervisor.setHitSetting(shooter, x, y, playerShips, enemyShips,
				accuracy);
		supervisor.losePoleSetting(victim, x, y, enemyShips, playerShips);
	}

	private void boardSettingSink(PlayerStatus shooter, PlayerStatus victim,
			int x, int y, int id) throws RemoteException {
		boardSettingHit(shooter, victim, x, y);
		supervisor.shipSunkSetting(shooter, id);
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
			int x, int y) throws RemoteException {
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(false);
		supervisor
				.missSetting(shooter, x, y, playerShips, enemyShips, accuracy);
		supervisor.allowToMove(victim, x, y);

	}

	/**
	 * 
	 * Metoda <code>getPresenter</code>ss
	 * 
	 * wi±¿e obiekt gracza z odpowiednim presenterem
	 *
	 * @param player
	 * @return presenter gracza
	 */
	private IShootingPresenterControll getPresenter(PlayerStatus player) {
		if (player.equals(player1))
			return pres1;
		else if (player.equals(player2))
			return pres2;
		else
			return null;
	}

	private PlayerStatus getOpposePlayer(PlayerStatus player) {
		if (player == player1)
			return player2;
		else if (player == player2)
			return player1;
		else
			return null;
	}

	public void gameOver(PlayerStatus player) {
		if (player.equals(player1)) {
			drawLeftShips(player1);
			pres1.gameOver(true);
			pres2.gameOver(false);

		} else if (player.equals(player2)) {
			drawLeftShips(player2);
			pres1.gameOver(false);
			pres2.gameOver(true);
		}

		pres1.changeGiveUpButtonLabel();
		pres2.changeGiveUpButtonLabel();

	}

	@Override
	public void resign(PlayerStatus player) {
		if (player.equals(player2)) {
			pres1.gameOver(true);
			pres2.gameOver(false);

		} else if (player.equals(player1)) {
			pres1.gameOver(false);
			pres2.gameOver(true);
		}

		drawLeftShips(player1);
		drawLeftShips(player2);
		pres1.changeGiveUpButtonLabel();
		pres2.changeGiveUpButtonLabel();
	}

	@Override
	public void callMenu() {
		pres1.closeFrame();
		pres2.closeFrame();

		supervisor.callMenu();
	}

	@Override
	public void setHint() {
		// brak tej opcji w tym trybie

	}
}
