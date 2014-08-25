package pl.praktykiatrem.game.battleship.graphic.setting;

import javax.swing.SwingUtilities;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingView;
import pl.praktykiatrem.game.battleship.rules.GameConstants;
import pl.praktykiatrem.game.uniElements.enums.Direction;

/**
 * presenter steruj¹cy widokiem do ustawiania statków na planszy
 * 
 * @author Filip Ró¿añski
 *
 */
public class SettingPresenter implements ISettingPresenter,
		ISettingPresenterControll {
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
	private PlayerStatus player;
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
	public SettingPresenter(GameConstants gameConst, PlayerStatus player,
			ISettingController controller) {
		this.gameConstants = gameConst;
		this.player = player;
		this.controller = controller;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view = new ShipSettingPanel(SettingPresenter.this);
				view.initialize(
						SettingPresenter.this.gameConstants.getShipTypes(),
						SettingPresenter.this.gameConstants.getBoardSizeV(),
						SettingPresenter.this.gameConstants.getBoardSizeH());
			}
		});

	}

	public SettingPresenter(GameConstants gameConst, PlayerStatus player,
			ISettingController controller, int mode) {
		this.gameConstants = gameConst;
		this.player = player;
		this.controller = controller;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view = new ShipSettingPanel(SettingPresenter.this);
				view.initialize(
						SettingPresenter.this.gameConstants.getShipTypes(),
						SettingPresenter.this.gameConstants.getBoardSizeV(),
						SettingPresenter.this.gameConstants.getBoardSizeH());
			}
		});

		controller.placeShipAtRandom(this, player);
	}

	@Override
	public void placeShipsOnView(int x, int y, Direction dir, int id,
			int polesNumber) {
		final int ID = id;

		drawOnBoard(x, y, dir, id + 1, polesNumber);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.setOkIconShipButton(ID, true);
				view.setReadyButtonState(gameConstants.getShipsNumber()
						- controller.getActiveShipsNumber(player));
			}
		});

	}

	/**
	 * losowo ustawia statki na planszy
	 */

	@Override
	public void resetBoard() {
		controller.resetGame(player);
		clearBoardView();
		if (ready)
			controller.playerIsNotReady();
	}

	private void clearBoardView() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < gameConstants.getBoardSizeV(); i++)
					for (int j = 0; j < gameConstants.getBoardSizeH(); j++) {
						view.changePlaceIcon(i, j, 0);
					}

				for (int i = 0; i < gameConstants.getShipsNumber(); i++)
					view.setOkIconShipButton(i, false);

				view.setReadyButtonState(gameConstants.getShipsNumber()
						- controller.getActiveShipsNumber(player));
			}
		});

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
		final int poles = polesNumber;
		final int X = x;
		final int Y = y;
		final int iconType = icon;
		final Direction direction = dir;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				if (direction == Direction.HORIZONTAL) {
					for (int i = 0; i < poles; i++)
						view.changePlaceIcon(X, Y + i, iconType);

				} else {
					for (int i = 0; i < poles; i++)
						view.changePlaceIcon(X + i, Y, iconType);
				}
			}
		});

	}

	@Override
	public void playerIsReady() {
		ready = true;
		controller.playerIsReady();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.disableReadyButton();

			}
		});
	}

	@Override
	public void showFrame() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.showFrame(player.getName());

			}
		});
	}

	@Override
	public void closeFrame() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.closeFrame();

			}
		});
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
				dir, new Coordinates(x, y)))
			placeShipsOnView(x, y, dir, id, gameConstants.getShipTypes()[id]);

		else if (controller.placeShips(player, id,
				gameConstants.getShipTypes()[id], Direction.getOpposite(dir),
				new Coordinates(x, y)))
			placeShipsOnView(x, y, Direction.getOpposite(dir), id,
					gameConstants.getShipTypes()[id]);

		else if (wasSet
				&& controller.placeShips(player, id, gameConstants
						.getShipTypes()[id], dir, new Coordinates(temp_x,
						temp_y)))
			placeShipsOnView(temp_x, temp_y, dir, id,
					gameConstants.getShipTypes()[id]);
	}

	private boolean displaceShip(int id) {
		Direction dir = player.getShip(id).getDirection();
		int x = player.getShip(id).getInitialCoords().getX();
		int y = player.getShip(id).getInitialCoords().getY();
		int polesNumber = gameConstants.getShipTypes()[id];
		final int ID = id;
		if (controller.displaceShip(player, id, polesNumber, dir,
				new Coordinates(x, y))) {
			drawOnBoard(x, y, dir, 0, polesNumber);
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					view.setOkIconShipButton(ID, false);
					view.setReadyButtonState(gameConstants.getShipsNumber()
							- controller.getActiveShipsNumber(player));
				}
			});
			return true;
		}
		return false;
	}

	@Override
	public void clickedRight(int x, int y) {
		int id = player.getShipID(new Coordinates(x, y));
		if (id >= 0 && player.isShipSet(id))
			displaceShip(id);
	}

	@Override
	public void clickedLeft(int x, int y) {
		int id = player.getShipID(new Coordinates(x, y));
		if (id >= 0) {
			x = player.getShip(id).getInitialCoords().getX();
			y = player.getShip(id).getInitialCoords().getY();
			Direction dir = Direction.getOpposite(getDirection(id));
			if (player.isShipSet(id))
				displaceShip(id);
			if (controller.placeShips(player, id,
					gameConstants.getShipTypes()[id], dir,
					new Coordinates(x, y)))
				placeShipsOnView(x, y, dir, id,
						gameConstants.getShipTypes()[id]);
			else if (controller.placeShips(player, id,
					gameConstants.getShipTypes()[id],
					Direction.getOpposite(dir), new Coordinates(x, y)))
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
		return player.getShipID(new Coordinates(x, y));
	}

	@Override
	public Direction getDirection(int id) {
		return player.getShip(id).getDirection();
	}

}
