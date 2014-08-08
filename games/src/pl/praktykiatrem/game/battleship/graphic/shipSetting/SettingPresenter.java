package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingView;
import pl.praktykiatrem.game.battleship.rules.GameConstants;

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
	 * zmienna u¿ywana do ustawiania staków, przechowuje liczbe masztów
	 * aktualnie wybranego statku
	 */
	private int polesNumber;
	/**
	 * zmienna u¿ywana do ustawiania statków, przechowuje ID aktualnie wybranego
	 * statku
	 */
	private int id;
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
	 * lista przycisków, które powinny byæ zablokowane
	 */
	private ArrayList<Coordinates> locked;
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
		locked = new ArrayList<Coordinates>();
		view = new ShipSettingPanel(this);
		view.initialize(gameConst.getShipTypes(), gameConst.getBoardSizeH(),
				gameConst.getBoardSizeV());
		view.changeStateAllBoardPlaces(false);
	}

	public SettingPresenter(GameConstants gameConst, BSPlayerStatus player,
			ISettingController controller, int mode) throws RemoteException {
		this.gameConstants = gameConst;
		this.player = player;
		this.controller = controller;
		locked = new ArrayList<Coordinates>();
		view = new ShipSettingPanel(this);
		view.initialize(gameConst.getShipTypes(), gameConst.getBoardSizeH(),
				gameConst.getBoardSizeV());
		view.changeStateAllBoardPlaces(false);
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

	/**
	 * tworzy listê zawieraj¹c¹ miejsca, na których ustawione zosta³y ju¿ statki
	 */
	private void getLockedPlaces() {
		ArrayList<Coordinates> tab = new ArrayList<Coordinates>();

		for (int a = 0; a < gameConstants.getShipsNumber(); a++)
			tab.addAll(controller.getCoordsList(player, a));

		this.locked = tab;
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter#shipChoiceDone(int,
	 *      int)
	 */
	@Override
	public void shipChoiceDone(int polesNumber, int id) {
		this.polesNumber = polesNumber;
		this.id = id;
		ArrayList<Coordinates> list = controller.getCoordsList(player, id);
		Coordinates[] tab = list.toArray(new Coordinates[list.size()]);
		if (!player.isShipSet(id)) {
			lockUsedPlaces();
		} else {
			view.disableAllBoardPlaces(tab[0].getX(), tab[0].getY());
		}
	}

	/**
	 * odblokowuje wszystkie miejsca, po czym blokuje te znajduj¹ce siê na
	 * liœcie locked
	 */
	private void lockUsedPlaces() {
		view.changeStateAllBoardPlaces(true);
		getLockedPlaces();
		for (Coordinates coord : locked)
			view.disableOneBoardPlace(coord.getX(), coord.getY());
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter#placeShip(int,
	 *      int, int)
	 */
	@Override
	public void placeShip(int x, int y, int freq) {
		switch (freq) {
		case 1:
			firstClick(x, y);
			break;
		case 2:
			clearLastChoice(x, y, Direction.HORIZONTAL);
			secondClick(x, y);
			break;
		case 0:
			clearLastChoice(x, y, Direction.VERTICAL);
			view.changeButtonCallNumber(x, y, 1);
			view.changeStateAllBoardPlaces(true);
			break;
		}
	}

	/**
	 * akcje wywolywane po pierwszym kliknieciu guzika
	 * 
	 * @param x
	 *            wspolrzedna guzika
	 * @param y
	 */
	private void firstClick(int x, int y) {
		if (controller.placeShips(player, id, polesNumber,
				Direction.HORIZONTAL, x, y))
			placeShipsOnView(x, y, Direction.HORIZONTAL, id, polesNumber);
		else
			secondClick(x, y);
	}

	/**
	 * akcje wywolywane po drugim klikniêciu guzika
	 * 
	 * @param x
	 *            wspo³rzêdna guzika
	 * @param y
	 */
	private void secondClick(int x, int y) {
		if (controller.placeShips(player, id, polesNumber, Direction.VERTICAL,
				x, y))
			placeShipsOnView(x, y, Direction.VERTICAL, id, polesNumber);
		else {
			view.changeButtonCallNumber(x, y, 1);
			view.changeStateAllBoardPlaces(true);
		}
	}

	@Override
	public void placeShipsOnView(int x, int y, Direction dir, int id,
			int polesNumber) {
		drawOnBoard(x, y, dir, id + 1, polesNumber);
		if (dir == Direction.VERTICAL)
			view.changeButtonCallNumber(x, y, 0);
		else
			view.changeButtonCallNumber(x, y, 2);
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
		if (controller.displaceShip(player, id, polesNumber, dir, x, y)) {
			drawOnBoard(x, y, dir, 0, polesNumber);
			view.setOkIconShipButton(id, false);
			view.setReadyButtonState(gameConstants.getShipsNumber()
					- controller.getActiveShipsNumber(player));
			getLockedPlaces();
			for (Coordinates coord : locked)
				view.disableOneBoardPlace(coord.getX(), coord.getY());
		}
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
		for (int i = 0; i < gameConstants.getBoardSizeH(); i++)
			for (int j = 0; j < gameConstants.getBoardSizeV(); j++) {
				view.changePlaceIcon(i, j, 0);
				view.changeButtonCallNumber(i, j, 1);
			}
		locked.clear();

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
	 * @param icon
	 *            typ ikony reprezentuj¹cej statek
	 */
	private void drawOnBoard(int x, int y, Direction dir, int icon,
			int polesNumber) {
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, icon);
				y++;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, icon);
				x++;
			}
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
}
