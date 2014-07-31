package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import java.util.ArrayList;
import java.util.Random;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

/**
 * presenter steruj�cy widokiem do ustawiania statk�w na planszy
 * 
 * @author Filip R�a�ski
 *
 */
public class SettingPresenter implements ISettingPresenter {
	/**
	 * zmienna u�ywana do ustawiania stak�w, przechowuje liczbe maszt�w
	 * aktualnie wybranego statku
	 */
	private int polesNumber;
	/**
	 * zmienna u�ywana do ustawiania statk�w, przechowuje ID aktualnie wybranego
	 * statku
	 */
	private int id;
	/**
	 * obiekt kt�ry koordynuje korzystanie z odpowiednich zasad
	 */
	private Game gameRules;
	/**
	 * reprezentacja gracza, kt�ry wykonuje swoje ruchy poprzez dany interfejs
	 */
	private PlayerStatus player;
	/**
	 * interfejs graficzny etapu ustawiania statk�w
	 */
	private ISettingView view;
	/**
	 * lista przycisk�w, kt�re powinny by� zablokowane
	 */
	private ArrayList<Coordinates> locked;
	/**
	 * obserwator zmiany etapu gry poprzez klikni�cie przycisku "ready"
	 */
	private SettingController controller;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>SettingPresenter</code>
	 *
	 * @param gameRules
	 * @param player
	 * @param observer
	 */
	public SettingPresenter(Game gameRules, PlayerStatus player,
			SettingController controller) {
		this.gameRules = gameRules;
		this.player = player;
		this.controller = controller;
		locked = new ArrayList<Coordinates>();
		view = new ShipSettingPanel(this);
		view.initialize(gameRules.getShipTypes(), gameRules.getBoardSizeH(),
				gameRules.getBoardSizeV());
		view.changeStateAllBoardPlaces(false);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingPresenter#getView()
	 */
	@Override
	public ISettingView getView() {
		return view;
	}

	/**
	 * tworzy list� zawieraj�c� miejsca, na kt�rych ustawione zosta�y ju� statki
	 */
	private void getLockedPlaces() {
		ArrayList<Coordinates> tab = new ArrayList<Coordinates>();

		for (int a = 0; a < gameRules.getShipsNumber(); a++)
			tab.addAll(gameRules.getCoordsList(player, a));

		this.locked = tab;
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingPresenter#shipChoiceDone(int,
	 *      int)
	 */
	@Override
	public void shipChoiceDone(int polesNumber, int id) {
		this.polesNumber = polesNumber;
		this.id = id;
		ArrayList<Coordinates> list = gameRules.getCoordsList(player, id);
		Coordinates[] tab = list.toArray(new Coordinates[list.size()]);
		if (!player.isShipSet(id)) {
			lockUsedPlaces();
		} else {
			view.disableAllBoardPlaces(tab[0].getX(), tab[0].getY());
		}
	}

	/**
	 * odblokowuje wszystkie miejsca, po czym blokuje te znajduj�ce si� na
	 * li�cie locked
	 */
	private void lockUsedPlaces() {
		view.changeStateAllBoardPlaces(true);
		getLockedPlaces();
		for (Coordinates coord : locked)
			view.disableOneBoardPlace(coord.getX(), coord.getY());
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingPresenter#placeShip(int,
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
		if (gameRules.placeShips(player, id, polesNumber, Direction.HORIZONTAL,
				x, y)) {
			drawOnBoard(x, y, Direction.HORIZONTAL, id + 1);
			view.changeButtonCallNumber(x, y, 2);
			view.setOkIconShipButton(id, true);
			view.setReadyButtonState(gameRules.getShipsNumber()
					- gameRules.getActiveShipsNumber(player));
		} else {
			secondClick(x, y);
		}
	}

	/**
	 * akcje wywolywane po drugim klikni�ciu guzika
	 * 
	 * @param x
	 *            wspo�rz�dna guzika
	 * @param y
	 */
	private void secondClick(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.VERTICAL,
				x, y)) {
			drawOnBoard(x, y, Direction.VERTICAL, id + 1);
			view.changeButtonCallNumber(x, y, 0);
			view.setOkIconShipButton(id, true);
			view.setReadyButtonState(gameRules.getShipsNumber()
					- gameRules.getActiveShipsNumber(player));
		} else {
			view.changeButtonCallNumber(x, y, 1);
		}

	}

	/**
	 * czysci poprzednio ustawione miejsca
	 * 
	 * @param x
	 *            wspolrz�dna guzika
	 * @param y
	 * @param dir
	 *            kierunek ustawienia statku
	 */
	public void clearLastChoice(int x, int y, Direction dir) {
		if (gameRules.displaceShips(player, id, polesNumber, dir, x, y)) {
			drawOnBoard(x, y, dir, 0);
			view.setOkIconShipButton(id, false);
			view.setReadyButtonState(gameRules.getShipsNumber()
					- gameRules.getActiveShipsNumber(player));
			getLockedPlaces();
			for (Coordinates coord : locked)
				view.disableOneBoardPlace(coord.getX(), coord.getY());
		}
	}

	/**
	 * losowo ustawia statki na planszy
	 */

	public void placeShipAtRandom() {
		Direction rand_dir;
		int rand_x;
		int rand_y;
		int rand_clickNumber;
		Random generator = new Random();
		for (int i = 0; i < gameRules.getShipsNumber(); i++) {
			this.polesNumber = gameRules.getShipTypes()[i];
			if (generator.nextBoolean() == true) {
				rand_dir = Direction.HORIZONTAL;
				rand_clickNumber = 2;
			} else {
				rand_dir = Direction.VERTICAL;
				rand_clickNumber = 0;
			}
			while (true) {
				rand_x = generator.nextInt(gameRules.getBoardSizeV());
				rand_y = generator.nextInt(gameRules.getBoardSizeH());
				if (gameRules.placeShips(player, i, polesNumber, rand_dir,
						rand_x, rand_y)) {
					drawOnBoard(rand_x, rand_y, rand_dir, i + 1);
					view.changeButtonCallNumber(rand_x, rand_y,
							rand_clickNumber);
					view.setOkIconShipButton(i, true);
					view.setReadyButtonState(gameRules.getShipsNumber()
							- gameRules.getActiveShipsNumber(player));
					break;

				}
			}
		}
	}

	/**
	 * rysuje statek w interfejsie graficznym
	 * 
	 * @param x
	 *            wsp�rz�dna guzika
	 * @param y
	 * @param dir
	 *            kieunek ustawienia statku
	 * @param icon
	 *            typ ikony reprezentuj�cej statek
	 */
	private void drawOnBoard(int x, int y, Direction dir, int icon) {
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
		controller.playerIsReady();
		view.disableReadyButton();
	}
}
