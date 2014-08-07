package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnlineServer;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;

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

	private StartGraphicOnlineServer supervisor;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingController</code>
	 *
	 * @param player1
	 * @param player2
	 * @param g
	 */
	public ShootingControllerOnline(PlayerStatus player1, PlayerStatus player2,
			Game g, StartGraphicOnlineServer supervisor) {
		this.player1 = player1;
		this.player2 = player2;
		this.supervisor = supervisor;
		this.g = g;

		try {
			pres1 = new ShootingPresenter(g, player1, this);
			pres2 = new ShootingPresenter(g, player2, this);
			pres1.setStats(g.getShipsNumber(), g.getShipsNumber());
			pres2.setStats(g.getShipsNumber(), g.getShipsNumber());

			pres1.changeStatus(true);
			pres2.changeStatus(false);

			pres1.showFrame();
			pres2.showFrame();
		} catch (RemoteException e) {
			System.out.println("shootingcontroller constructor");
			e.printStackTrace();
			System.exit(0);
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
	 * @return true je¶li trafiony, inaczej false
	 */
	public boolean makeMove(PlayerStatus player, int x, int y) {
		if (player.equals(player1)) {
			int result = g.makeMove(player2, x, y);
			if (result >= 1) {
				boardSettingHit(player1, player2, x, y);
				if (result == 2) {
					int id = g.getShipID(player2, x, y);
					try {
						pres1.drawShip(g.getCoordsTable(player2, id));
					} catch (RemoteException e) {
						System.out.println("makeMove");
						e.printStackTrace();
						System.exit(0);
					}
					if (player2.getShipsNumber() == 0) {
						gameOver(player1);
					}
				}
				return true;
			} else {
				boardSettingMiss(player1, player2, x, y);
				return false;
			}
		} else {
			int result = g.makeMove(player1, x, y);
			if (result >= 1) {
				boardSettingHit(player2, player1, x, y);
				if (result == 2) {
					int id = g.getShipID(player1, x, y);
					try {
						pres2.drawShip(g.getCoordsTable(player1, id));
					} catch (RemoteException e) {
						System.out.println("makeMove");
						e.printStackTrace();
						System.exit(0);
					}
					if (player1.getShipsNumber() == 0) {
						gameOver(player2);
					}
				}
				return true;
			} else {
				boardSettingMiss(player2, player1, x, y);
				return false;
			}
		}
	}

	private void drawLeftShips1() {
		for (int j = 0; j < g.getBoardSizeV(); j++)
			for (int i = 0; i < g.getBoardSizeH(); i++)
				if (player1.getPlace(i, j).isShipOnPlace()
						&& player1.getPlace(i, j).isPlaceInGame())
					try {
						pres2.fchangeIcon(i, j, player1.getPlace(i, j)
								.getShipId() + 1);
					} catch (RemoteException e) {
						System.out.println("drawLeftships");
						e.printStackTrace();
						System.exit(0);
					}
	}

	private void drawLeftShips2() {
		for (int j = 0; j < g.getBoardSizeV(); j++)
			for (int i = 0; i < g.getBoardSizeH(); i++)
				if (player2.getPlace(i, j).isShipOnPlace()
						&& player2.getPlace(i, j).isPlaceInGame())
					try {
						pres1.fchangeIcon(i, j, player2.getPlace(i, j)
								.getShipId() + 1);
					} catch (RemoteException e) {
						System.out.println("drawLeftships");
						e.printStackTrace();
						System.exit(0);
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
		IShootingPresenterControll sPres = getPresenter(shooter);
		IShootingPresenterControll vPres = getPresenter(victim);

		try {
			vPres.changeStateIcon(x, y, 0);
			sPres.changeBattlePlaceIcon(x, y, 2);
			playerShips = g.getActiveShipsNumber(shooter);
			enemyShips = g.getActiveShipsNumber(victim);
			accuracy = shooter.getAccuracy(true);
			sPres.setStats(playerShips, enemyShips, accuracy);
			vPres.setStats(enemyShips, playerShips);
		} catch (RemoteException e) {
			System.out.println("boardSettingHit");
			e.printStackTrace();
			System.exit(0);
		}
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
		IShootingPresenterControll sPres = getPresenter(shooter);
		IShootingPresenterControll vPres = getPresenter(victim);

		try {
			vPres.changeStateIcon(x, y, 1);
			vPres.changeStatus(true);
			sPres.changeStatus(false);
			playerShips = g.getActiveShipsNumber(shooter);
			enemyShips = g.getActiveShipsNumber(victim);
			accuracy = shooter.getAccuracy(false);
			sPres.setStats(playerShips, enemyShips, accuracy);
			vPres.setStats(enemyShips, playerShips);
		} catch (RemoteException e) {
			System.out.println("boardSettingMiss");
			e.printStackTrace();
			System.exit(0);
		}
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

	public void gameOver(PlayerStatus player) {
		try {
			if (player.equals(player1)) {
				drawLeftShips1();
				pres1.gameOver(true);
				pres2.gameOver(false);

			} else if (player.equals(player2)) {
				drawLeftShips2();
				pres1.gameOver(false);
				pres2.gameOver(true);
			}

			pres1.changeGiveUpButtonLabel();
			pres2.changeGiveUpButtonLabel();
		} catch (RemoteException e) {
			System.out.println("gameOver");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void resign(PlayerStatus player) {
		try {
			if (player.equals(player2)) {
				pres1.gameOver(true);
				pres2.gameOver(false);

			} else if (player.equals(player1)) {
				pres1.gameOver(false);
				pres2.gameOver(true);
			}

			drawLeftShips1();
			drawLeftShips2();
			pres1.changeGiveUpButtonLabel();
			pres2.changeGiveUpButtonLabel();
		} catch (RemoteException e) {
			System.out.println("resign");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void callMenu() {
		try {
			pres1.closeFrame();
			pres2.closeFrame();
		} catch (RemoteException e) {
			System.out.println("callMenu");
			e.printStackTrace();
			System.exit(0);
		}
		supervisor.callMenu();

	}
}
