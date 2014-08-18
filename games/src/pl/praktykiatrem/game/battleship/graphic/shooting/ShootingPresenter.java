package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingView;
import pl.praktykiatrem.game.battleship.rules.Game;

/**
 * 
 * Klasa <code>ShootingPresenterRMI</code>
 *
 * odpowiada za sterowanie zachowaniem interfejsu graficznego fazy strzelania
 *
 * @author filipr
 * @version 30 lip 2014 15:18:49
 *
 */
public class ShootingPresenter extends UnicastRemoteObject implements
		IShootingPresenter, IShootingPresenterControll {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3263489986702278924L;
	/**
	 * obiekt udostêpniaj±cy akcje wykonywane w trakcie gry
	 */
	private Game gameRules;
	/**
	 * obiekt reprezentuj±cy gracza, który korzysta ze sterowanego interfejsu
	 */
	private BSPlayerStatus player;
	/**
	 * obiekt przechowuj±cy interfejs graficzny
	 */
	private IShootingView view;
	/**
	 * obiekt reprezentuj±cy controller nadzoruj±cy fazê strzelania
	 */
	private IShootingController controll;
	/**
	 * lista miejsc, które zawsze pozostaj± zablokowane
	 */
	private ArrayList<Coordinates> lockedPlaces;

	/**
	 * przechowuje status gry (T/F)
	 */
	private boolean gameOver = false;

	private int giveUpButtonCallNumber;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingPresenterRMI</code>
	 *
	 * @param gameRules
	 * @param player
	 * @param controll
	 */
	public ShootingPresenter(Game gameRules, BSPlayerStatus player,
			IShootingController controll) throws RemoteException {
		this.gameRules = gameRules;
		this.player = player;
		this.controll = controll;
		this.lockedPlaces = new ArrayList<Coordinates>();
		giveUpButtonCallNumber = 0;

		view = new ShootingPanel(this);
		view.initialize(gameRules.getBoardSizeV(), gameRules.getBoardSizeH());
		drawShips();
		view.disableAllPlayerBoardPlaces();
	}

	/**
	 * 
	 * Metoda <code>drawShips</code>
	 * 
	 * rysuje wcze¶niej ustawione statki na planszy gracza
	 *
	 */
	private void drawShips() {
		for (int i = 0; i < gameRules.getShipsNumber(); i++)
			view.drawShipLocation(gameRules.getCoordsTable(player, i), i);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#shot(int,
	 *      int)
	 */
	@Override
	public void shot(int x, int y) {
		if (!gameOver) {
			if (controll.makeMove(player, x, y)) {
				// view.changeBattlePlaceIcon(x, y, 10);
				view.disableBatlleBoardPlace(x, y);
				lockedPlaces.add(new Coordinates(x, y));
			} else {
				view.changeBattlePlaceIcon(x, y, 1);
			}
		}
	}

	@Override
	public void changeBattlePlaceIcon(int x, int y, int type) {
		view.changeBattlePlaceIcon(x, y, type);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#changeIcon(int,
	 *      int, int)
	 */
	@Override
	public void changeIcon(int x, int y, int type) {
		view.changePlaceIcon(x, y, type);
	}

	@Override
	public void fchangeIcon(int x, int y, int type) {
		view.fchangeBattlePlaceIcon(x, y, type);
	}

	@Override
	public void changeStateIcon(int x, int y, int type) {
		view.changePlaceStateIcon(x, y, type);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#changeStatus(boolean)
	 */
	@Override
	public void changeStatus(boolean ableToMove) {
		view.changeStateAllEnemyBoardPlaces(ableToMove, lockedPlaces);
		view.changeStatus(ableToMove);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#setStats(int,
	 *      int, int)
	 */
	@Override
	public void setStats(int playerShips, int enemyShips, int accuracy) {
		view.setStats(playerShips, enemyShips, accuracy);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#setStats(int,
	 *      int)
	 */
	@Override
	public void setStats(int playerShips, int enemyShips) {
		view.setStats(playerShips, enemyShips);
	}

	@Override
	public void drawShip(Coordinates[] tab) {
		for (Coordinates c : tab) {
			view.changeBattlePlaceIcon(c.getX(), c.getY(), 0);
		}
	}

	@Override
	public void gameOver(boolean win) {
		view.GameOver(win);
		this.gameOver = true;
	}

	@Override
	public void giveUp() {
		giveUpButtonCallNumber++;

		if (giveUpButtonCallNumber == 1) {
			controll.resign(player);
			changeGiveUpButtonLabel();
		} else if (giveUpButtonCallNumber == 2) {
			controll.callMenu();
		}
	}

	@Override
	public void changeGiveUpButtonLabel() {
		view.changeGiveUpButtonLabel("PrzejdŸ do menu");
		giveUpButtonCallNumber = 1;
	}

	@Override
	public void showFrame() {
		view.showFrame(player.getName());
	}

	@Override
	public void closeFrame() {
		view.closeFrame();
	}

	@Override
	public void showHint() {
		controll.setHint();
	}
}
