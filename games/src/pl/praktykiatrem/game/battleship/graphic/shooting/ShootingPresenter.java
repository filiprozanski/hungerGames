package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingView;
import pl.praktykiatrem.game.battleship.rules.GameConstants;

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
public class ShootingPresenter implements IShootingPresenter,
		IShootingPresenterControll {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3263489986702278924L;
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
	private IShootingController controller;
	/**
	 * lista miejsc, które zawsze pozostaj± zablokowane
	 */
	private ArrayList<Coordinates> lockedPlaces;

	/**
	 * przechowuje status gry (T/F)
	 */
	private boolean gameOver = false;

	private int giveUpButtonCallNumber;

	private GameConstants gameConstants;

	private int x = -1;
	private int y = -1;

	public ShootingPresenter(GameConstants constants,
			IShootingController controll) {
		this.gameConstants = constants;
		this.player = player;
		this.controller = controll;
		this.lockedPlaces = new ArrayList<Coordinates>();
		giveUpButtonCallNumber = 0;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view = new ShootingPanel(ShootingPresenter.this);
				view.initialize(
						ShootingPresenter.this.gameConstants.getShipTypes(),
						ShootingPresenter.this.gameConstants.getBoardSizeV(),
						ShootingPresenter.this.gameConstants.getBoardSizeH());
				view.disableAllPlayerBoardPlaces();
			}
		});

		drawShips();

	}

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingPresenterRMI</code>
	 *
	 * @param gameRules
	 * @param player
	 * @param controller
	 */
	public ShootingPresenter(GameConstants gameConstants,
			IShootingController controll, boolean move) {
		this(gameConstants, controll);
		final boolean m = move;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.changeStateAllEnemyBoardPlaces(m, lockedPlaces);
				view.changeStatus(m);
			}
		});

	}

	/**
	 * 
	 * Metoda <code>drawShips</code>
	 * 
	 * rysuje wcze¶niej ustawione statki na planszy gracza
	 *
	 */
	private void drawShips() {
		final int shipsNumber = gameConstants.getShipsNumber();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < shipsNumber; i++)
					view.drawShipLocation(controller.getCoordsTable(i), i);
			}
		});
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#shot(int,
	 *      int)
	 */
	@Override
	public void shot(int x, int y) {
		this.x = x;
		this.y = y;

		try {
			controller.makeMove(player, new Coordinates(x, y));
		} catch (RemoteException e) {
			System.out.println("makeMove presenter");
			e.printStackTrace();
		}
	}

	@Override
	public void changeBattlePlaceIcon(Coordinates coords, int type) {
		final int x = coords.getX();
		final int y = coords.getY();
		final int typeInside = type;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.changeBattlePlaceIcon(x, y, typeInside);
				view.disableBatlleBoardPlace(x, y);
				lockedPlaces.add(new Coordinates(x, y));

			}
		});
	}

	@Override
	public void fchangeIcon(Coordinates coords, int type) {
		final int x = coords.getX();
		final int y = coords.getY();
		final int typeInside = type;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.changeEnemyBattlePlaceIcon(x, y, typeInside);
			}
		});

	}

	@Override
	public void changeStateIcon(Coordinates coords, int type) {
		final int x = coords.getX();
		final int y = coords.getY();
		final int typeInside = type;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.changePlaceStateIcon(x, y, typeInside);
			}
		});
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#changeStatus(boolean)
	 */
	@Override
	public void changeStatus(boolean ableToMove) {
		final boolean move = ableToMove;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.changeStateAllEnemyBoardPlaces(move, lockedPlaces);
				view.changeStatus(move);
			}
		});

	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#setStats(int,
	 *      int, int)
	 */
	@Override
	public void setStats(int playerShips, int enemyShips, int accuracy) {
		final int player = playerShips;
		final int enemy = enemyShips;
		final int acc = accuracy;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.setStats(player, enemy, acc);
			}
		});
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenter#setStats(int,
	 *      int)
	 */
	@Override
	public void setStats(int playerShips, int enemyShips) {
		final int player = playerShips;
		final int enemy = enemyShips;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.setStats(player, enemy);
			}
		});
	}

	@Override
	public void drawShip(Coordinates[] tab) {
		final Coordinates[] tabela = tab;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				for (Coordinates c : tabela) {
					view.changeBattlePlaceIcon(c.getX(), c.getY(), 0);
				}
			}
		});
	}

	@Override
	public void gameOver(boolean win) {
		final boolean winner = win;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.GameOver(winner);
			}
		});
		this.gameOver = true;
	}

	@Override
	public void giveUp() {
		giveUpButtonCallNumber++;

		if (giveUpButtonCallNumber == 1) {
			controller.resign(player);
			changeGiveUpButtonLabel();
		} else if (giveUpButtonCallNumber == 2) {
			controller.endGame();
			controller.setHint();
			closeFrame();
		}
	}

	@Override
	public void changeGiveUpButtonLabel() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.changeGiveUpButtonLabel("PrzejdŸ do menu");
			}
		});

		giveUpButtonCallNumber = 1;
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
	public void changeShipState(int shipID) {
		final int id = shipID;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				view.changeShipState(id);
			}
		});
	}

	@Override
	public void showHint() {
		controller.setHint();
	}
}
