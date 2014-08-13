package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingView;
import pl.praktykiatrem.game.battleship.rules.GameConstants;
import pl.praktykiatrem.game.uniElements.enums.Direction;

/**
 * presenter steruj¹cy widokiem do ustawiania statków na planszy
 * 
 * @author Filip Ró¿añski
 *
 */
public class SettingPresenter extends UnicastRemoteObject implements
		ISettingPresenter, ISettingPresenterControll {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8153221086917373820L;

	/**
	 * obiekt który koordynuje korzystanie z odpowiednich zasad
	 */
	private GameConstants gameConstants;
	/**
	 * reprezentacja gracza, który wykonuje swoje ruchy poprzez dany interfejs
	 */
	private BSPlayerStatus player;
	/**
	 * interfejs graficzny etapu ustawiania statków
	 */
	private ISettingView view;
	/**
	 * obserwator zmiany etapu gry poprzez klikniêcie przycisku "ready"
	 */
	private ISettingController controller;

	private boolean ready;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>SettingPresenter</code>
	 *
	 * @param gameConst
	 * @param player
	 * @param observer
	 */
	public SettingPresenter(GameConstants gameConst, BSPlayerStatus player,
			ISettingController controller) throws RemoteException {
		this.gameConstants = gameConst;
		this.player = player;
		this.controller = controller;
		view = new ShipSettingPanel(this);
		view.initialize(gameConst.getShipTypes(), gameConst.getBoardSizeV(),
				gameConst.getBoardSizeH());
		// view.changeStateAllBoardPlaces(false);
	}

	public SettingPresenter(GameConstants gameConst, BSPlayerStatus player,
			ISettingController controller, int mode) throws RemoteException {
		this.gameConstants = gameConst;
		this.player = player;
		this.controller = controller;
		view = new ShipSettingPanel(this);
		view.initialize(gameConst.getShipTypes(), gameConst.getBoardSizeV(),
				gameConst.getBoardSizeH());
		// view.changeStateAllBoardPlaces(false);
		controller.placeShipAtRandom(this, player);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter#getView()
	 */
	@Override
	public ISettingView getView() {
		return view;
	}

	@Override
	public void placeShipsOnView(int x, int y, Direction dir, int id,
			int polesNumber) {
		drawOnBoard(x, y, dir, id + 1, polesNumber);
		view.setOkIconShipButton(id, true);
		view.setReadyButtonState(gameConstants.getShipsNumber()
				- controller.getActiveShipsNumber(player));
	}

	/**
	 * czysci poprzednio ustawione miejsca
	 * 
	 * @param x
	 *            wspolrzêdna guzika
	 * @param y
	 * @param dir
	 *            kierunek ustawienia statku
	 */

	public void clearLastChoice(int x, int y, Direction dir) {

	}

	/**
	 * losowo ustawia statki na planszy
	 */

	public void resetBoard() {
		controller.resetGame(player);
		clearBoardView();
		if (ready)
			controller.playerIsNotReady();
	}

	private void clearBoardView() {
		for (int i = 0; i < gameConstants.getBoardSizeV(); i++)
			for (int j = 0; j < gameConstants.getBoardSizeH(); j++) {
				view.changePlaceIcon(i, j, 0);
			}

		for (int i = 0; i < gameConstants.getShipsNumber(); i++)
			view.setOkIconShipButton(i, false);

		view.setReadyButtonState(gameConstants.getShipsNumber()
				- controller.getActiveShipsNumber(player));
	}

	/**
	 * rysuje statek w interfejsie graficznym
	 * 
	 * @param x
	 *            wspó³rzêdna guzika
	 * @param y
	 * @param dir
	 *            kieunek ustawienia statku
	 * @param IconPainting
	 *            typ ikony reprezentuj¹cej statek
	 */
	private void drawOnBoard(int x, int y, Direction dir, int icon,
			int polesNumber) {
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++)
				view.changePlaceIcon(x, y + i, icon);

		} else {
			for (int i = 0; i < polesNumber; i++)
				view.changePlaceIcon(x + i, y, icon);

		}
	}

	public void playerIsReady() {
		ready = true;
		controller.playerIsReady();
		view.disableReadyButton();
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
	public void placeShipAtRandom() {
		controller.placeShipAtRandom(this, player);
	}

	@Override
	public void dropShip(int id, int x, int y, Direction dir) {
		int temp_x = -1;
		int temp_y = -1;
		boolean wasSet = false;
		if (player.isShipSet(id)) {
			wasSet = true;
			temp_x = player.getShip(id).getInitialCoords().getX();
			temp_y = player.getShip(id).getInitialCoords().getY();
			displaceShip(id);
		}
		if (controller.placeShips(player, id, gameConstants.getShipTypes()[id],
				dir, x, y))
			placeShipsOnView(x, y, dir, id, gameConstants.getShipTypes()[id]);

		else if (controller.placeShips(player, id,
				gameConstants.getShipTypes()[id], Direction.getOpposite(dir),
				x, y))
			placeShipsOnView(x, y, Direction.getOpposite(dir), id,
					gameConstants.getShipTypes()[id]);

		else if (wasSet
				&& controller.placeShips(player, id,
						gameConstants.getShipTypes()[id], dir, temp_x, temp_y))
			placeShipsOnView(temp_x, temp_y, dir, id,
					gameConstants.getShipTypes()[id]);
	}

	private boolean displaceShip(int id) {
		Direction dir = player.getShip(id).getDirection();
		int x = player.getShip(id).getInitialCoords().getX();
		int y = player.getShip(id).getInitialCoords().getY();
		int polesNumber = gameConstants.getShipTypes()[id];
		if (controller.displaceShip(player, id, polesNumber, dir, x, y)) {
			drawOnBoard(x, y, dir, 0, polesNumber);
			view.setOkIconShipButton(id, false);
			view.setReadyButtonState(gameConstants.getShipsNumber()
					- controller.getActiveShipsNumber(player));
			return true;
		}
		return false;
	}

	@Override
	public void clickedRight(int x, int y) {
		int id = player.getShipID(x, y);
		if (id >= 0 && player.isShipSet(id))
			displaceShip(id);
	}

	@Override
	public void clickedLeft(int x, int y) {
		int id = player.getShipID(x, y);
		if (id >= 0) {
			x = player.getShip(id).getInitialCoords().getX();
			y = player.getShip(id).getInitialCoords().getY();
			Direction dir = Direction.getOpposite(getDirection(id));
			if (player.isShipSet(id))
				displaceShip(id);
			if (controller.placeShips(player, id,
					gameConstants.getShipTypes()[id], dir, x, y))
				placeShipsOnView(x, y, dir, id,
						gameConstants.getShipTypes()[id]);
			else if (controller.placeShips(player, id,
					gameConstants.getShipTypes()[id],
					Direction.getOpposite(dir), x, y))
				placeShipsOnView(x, y, Direction.getOpposite(dir), id,
						gameConstants.getShipTypes()[id]);
		}
	}

	@Override
	public int getPolesNumber(int id) {
		return gameConstants.getShipType(id);
	}

	@Override
	public int getID(int x, int y) {
		return player.getShipID(x, y);
	}

	@Override
	public Direction getDirection(int id) {
		return player.getShip(id).getDirection();
	}

}
