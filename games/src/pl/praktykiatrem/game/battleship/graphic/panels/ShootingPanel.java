package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;
import pl.praktykiatrem.game.battleship.graphic.observers.IShootingButtonsObserver;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView;

/**
 * 
 * Klasa <code>ShootingPanel</code> reprezentuje panel gry u¿ywany podczas
 * w³a¶ciwej rozgrywki (strzelania)
 *
 * @author filipr
 * @version 30 lip 2014 14:45:11
 *
 */
public class ShootingPanel extends JPanel implements IBoardPlaceObserver,
		IShootingView, IShootingButtonsObserver {

	/**
	 * Pole <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8558313104370220473L;
	/**
	 * panel zawieraj±cy widok planszy gracza
	 */
	private BoardGraphicPanel playerBoardPanel;
	/**
	 * panel zawieraj±cy widok plaszy przeciwnika
	 */
	private BoardGraphicPanel enemyBoardPanel;
	/**
	 * panel zawieraj±cy informacjê o grze przydatne w trakcie gry
	 */
	private InfoPanel infoPanel;
	/**
	 * panel zawieraj±cy u¿ywanych w trakcie rzgrywki oznaczeñ
	 */
	private LegendPanel legendPanel;
	/**
	 * presenter steruj±cy wygl±dem i dzia³aniem panelu
	 */
	private IShootingPresenter presenter;

	private JFrame frame;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingPanel</code>
	 *
	 * @param presenter
	 */
	public ShootingPanel(IShootingPresenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#initialize(int,
	 *      int)
	 */
	@Override
	public void initialize(int sizeH, int sizeV) {
		setLayout(new GridLayout(2, 2));
		playerBoardPanel = new BoardGraphicPanel(sizeH, sizeV);
		enemyBoardPanel = new BoardGraphicPanel(this, sizeH, sizeV);
		infoPanel = new InfoPanel(this);
		legendPanel = new LegendPanel();

		add(playerBoardPanel);
		add(enemyBoardPanel);
		add(infoPanel);
		add(legendPanel);

		repaint();
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#changePlaceIcon(int,
	 *      int, int)
	 */
	@Override
	public void changePlaceIcon(int x, int y, int type) {
		playerBoardPanel.changePlaceIcon(x, y, type);
	}

	@Override
	public void fchangeBattlePlaceIcon(int x, int y, int type) {
		enemyBoardPanel.changePlaceIcon(x, y, type);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#changeBattlePlaceIcon(int,
	 *      int, int)
	 */
	@Override
	public void changeBattlePlaceIcon(int x, int y, int type) {
		enemyBoardPanel.changePlaceStateIcon(x, y, type);
	}

	/**
	 * 
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(660, 660);
	}

	/**
	 * 
	 * @see javax.swing.JComponent#getMinimumSize()
	 */
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(660, 660);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#disableBatlleBoardPlace(int,
	 *      int)
	 */
	@Override
	public void disableBatlleBoardPlace(int x, int y) {
		enemyBoardPanel.disableButton(x, y);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#disableAllPlayerBoardPlaces()
	 */
	@Override
	public void disableAllPlayerBoardPlaces() {
		playerBoardPanel.changeStateAllButtons(false);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#changeStateAllEnemyBoardPlaces(boolean,
	 *      java.util.ArrayList)
	 */
	@Override
	public void changeStateAllEnemyBoardPlaces(boolean enable,
			ArrayList<Coordinates> lockedPlaces) {
		enemyBoardPanel.changeStateAllButtons(enable);

		if (enable) {
			int x = 0;
			int y = 0;

			for (Coordinates a : lockedPlaces) {
				x = a.getX();
				y = a.getY();

				enemyBoardPanel.disableButton(x, y);
			}
		}
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#drawShipLocation(pl.praktykiatrem.game.battleship.gameComponents.Coordinates[],
	 *      int)
	 */
	@Override
	public void drawShipLocation(Coordinates[] tab, int id) {
		for (Coordinates coord : tab)
			changePlaceIcon(coord.getX(), coord.getY(), id + 1);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver#clicked(int,
	 *      int, int)
	 */
	@Override
	public void clicked(int x, int y, int freq) {
		presenter.shot(x, y);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#changeStatus(boolean)
	 */
	@Override
	public void changeStatus(boolean ready) {
		if (ready == true)
			infoPanel.changeStatus(1);
		else if (ready == false)
			infoPanel.changeStatus(0);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#setStats(int,
	 *      int, int)
	 */
	@Override
	public void setStats(int playerShips, int enemyShips, int accuracy) {
		infoPanel.setStats(playerShips, enemyShips, accuracy);
	}

	/**
	 * 
	 * @see pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView#setStats(int,
	 *      int)
	 */
	@Override
	public void setStats(int playerShips, int enemyShips) {
		infoPanel.setStats(playerShips, enemyShips);
	}

	@Override
	public void GameOver(boolean win) {
		changeStateAllEnemyBoardPlaces(false, null);
		if (win == true)
			infoPanel.changeStatus(3);
		else
			infoPanel.changeStatus(2);
	}

	@Override
	public void changePlaceStateIcon(int x, int y, int type) {
		playerBoardPanel.changePlaceStateIcon(x, y, type);

	}

	@Override
	public void giveUpButtonclicked() {
		presenter.giveUp();
	}

	@Override
	public void changeGiveUpButtonLabel(String text) {
		infoPanel.changeGiveUpButtonLabel(text);
	}

	@Override
	public void showFrame(String name) {
		frame = new JFrame(name);
		frame.setResizable(false);
		frame.setBackground(new Color(135, 206, 235));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setSize(660, 660);
		frame.getContentPane().add(this);
		frame.setVisible(true);
	}

	@Override
	public void closeFrame() {
		frame.dispose();
	}
}
