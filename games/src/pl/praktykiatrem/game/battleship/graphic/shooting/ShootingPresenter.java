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
	 * obiekt udostępniający akcje wykonywane w trakcie gry
	 */
	private Game gameRules;
	/**
	 * obiekt reprezentujący gracza, który korzysta ze sterowanego interfejsu
	 */
	private PlayerStatus player;
	/**
	 * obiekt przechowujący interfejs graficzny
	 */
	private IShootingView view;
	/**
	 * obiekt reprezentujący controller nadzorujący fazę strzelania
	 */
	private ShootingController controll;
	/**
	 * lista miejsc, które zawsze pozostają zablokowane
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
		view.initialize(gameRules.getBoardSize_H(), gameRules.getBoardSize_V());
		drawShips();
		view.disableAllPlayerBoardPlaces();
	}

	/**
	 * 
	 * Metoda <code>drawShips</code>
	 * 
	 * rysuje wcześniej ustawione statki na planszy gracza
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
			view.changeBattlePlaceIcon(x, y, 10);
			view.disableBatlleBoardPlace(x, y);
			lockedPlaces.add(new Coordinates(x, y));
		} else
			view.changeBattlePlaceIcon(x, y, 9);
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
}
