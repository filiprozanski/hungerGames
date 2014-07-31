package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

/**
 * 
 * Klasa <code>ShootingPresenter</code>
 *
 * odpowiada za sterowanie zachowaniem interfejsu graficznego fazy strzelania
 *
 * @author filipr
 * @version 30 lip 2014 15:18:49
 *
 */
public class ShootingPresenter implements IShootingPresenter {
	/**
	 * obiekt udostêpniaj±cy akcje wykonywane w trakcie gry
	 */
	private Game gameRules;
	/**
	 * obiekt reprezentuj±cy gracza, który korzysta ze sterowanego interfejsu
	 */
	private PlayerStatus player;
	/**
	 * obiekt przechowuj±cy interfejs graficzny
	 */
	private IShootingView view;
	/**
	 * obiekt reprezentuj±cy controller nadzoruj±cy fazê strzelania
	 */
	private ShootingController controll;
	/**
	 * lista miejsc, które zawsze pozostaj± zablokowane
	 */
	private ArrayList<Coordinates> lockedPlaces;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingPresenter</code>
	 *
	 * @param gameRules
	 * @param player
	 * @param controll
	 */
	public ShootingPresenter(Game gameRules, PlayerStatus player,
			ShootingController controll) {
		this.gameRules = gameRules;
		this.player = player;
		this.controll = controll;
		this.lockedPlaces = new ArrayList<Coordinates>();

		view = new ShootingPanel(this);
		view.initialize(gameRules.getBoardSizeH(), gameRules.getBoardSizeV());
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
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter#getView()
	 */
	@Override
	public IShootingView getView() {
		return view;
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter#shot(int,
	 *      int)
	 */
	@Override
	public void shot(int x, int y) {
		if (controll.makeMove(player, x, y)) {
			// view.changeBattlePlaceIcon(x, y, 10);
			view.disableBatlleBoardPlace(x, y);
			lockedPlaces.add(new Coordinates(x, y));
		} else
			view.changeBattlePlaceIcon(x, y, 1);
	}

	@Override
	public void changeBattlePlaceIcon(int x, int y, int type) {
		view.changeBattlePlaceIcon(x, y, type);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter#changeIcon(int,
	 *      int, int)
	 */
	@Override
	public void changeIcon(int x, int y, int type) {
		view.changePlaceIcon(x, y, type);
	}

	@Override
	public void changeStateIcon(int x, int y, int type) {
		view.changePlaceStateIcon(x, y, type);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter#changeStatus(boolean)
	 */
	@Override
	public void changeStatus(boolean ableToMove) {
		view.changeStateAllEnemyBoardPlaces(ableToMove, lockedPlaces);
		view.changeStatus(ableToMove);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter#setStats(int,
	 *      int, int)
	 */
	@Override
	public void setStats(int playerShips, int enemyShips, int accuracy) {
		view.setStats(playerShips, enemyShips, accuracy);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter#setStats(int,
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
}
