package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;
import pl.praktykiatrem.game.battleship.graphic.observers.ISettingButtonsObserver;
import pl.praktykiatrem.game.battleship.graphic.observers.IShipChoiceObserver;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingView;
import pl.praktykiatrem.game.uniElements.enums.Direction;

/**
 * Panel zbieraj¹cy wszystkie elementy interfejsu u¿ytkownika potrzebne podczas
 * ustawiania statków
 * 
 * @author Filip Ró¿añski
 *
 */
public class ShipSettingPanel extends JPanel implements IBoardPlaceObserver,
		ISettingView, IShipChoiceObserver, ISettingButtonsObserver {
	private static final long serialVersionUID = 1189452196940629120L;
	private BoardGraphicPanel boardPanel;
	private ShipChoicePanel choicePanel;
	private ButtonsPanel readyPanel;
	private LegendPanel legendPanel;
	private ISettingPresenter presenter;
	private JFrame frame;

	public ShipSettingPanel(ISettingPresenter presenter) {

		this.presenter = presenter;
	}

	@Override
	public void initialize(int[] shipTypes, int sizeV, int sizeH) {
		setLayout(new GridLayout(2, 2));
		boardPanel = new BoardGraphicPanel(this, sizeV, sizeH, true);
		choicePanel = new ShipChoicePanel(this, shipTypes);
		readyPanel = new ButtonsPanel(this);
		legendPanel = new LegendPanel();
		add(boardPanel);
		add(choicePanel);
		add(readyPanel);
		add(legendPanel);

		repaint();
	}

	@Override
	public void changePlaceIcon(int x, int y, int type) {
		boardPanel.changePlaceIcon(x, y, type);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(660, 660);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(660, 660);
	}

	public void enableShipButton(int id) {
		choicePanel.enableShipButton(id);
	}

	@Override
	public void setOkIconShipButton(int id, boolean ok) {
		choicePanel.setOkIconShipButton(id, ok);

	}

	public void disableShipButton(int id) {
		choicePanel.disableShipButton(id);
	}

	@Override
	public void disableReadyButton() {
		readyPanel.diableButton();
	}

	@Override
	public void setReadyButtonState(int state) {
		readyPanel.setReadyButtonState(state);
	}

	@Override
	public void resetButtonClicked() {
		presenter.resetBoard();
	}

	@Override
	public void randomButtonClicked() {
		presenter.placeShipAtRandom();
	}

	@Override
	public void playerIsReady() {
		presenter.playerIsReady();
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
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame.dispose();

			}
		});
	}

	@Override
	public void dropShip(int id, int x, int y, Direction dir) {
		presenter.dropShip(id, x, y, dir);
	}

	@Override
	public void clickedLeft(int x, int y) {
		presenter.clickedLeft(x, y);

	}

	@Override
	public int getPolesNumber(int id) {
		return presenter.getPolesNumber(id);
	}

	@Override
	public int getID(int x, int y) {
		return presenter.getID(x, y);
	}

	@Override
	public Direction getDirection(int id) {
		return presenter.getDirection(id);
	}

	@Override
	public void clickedRight(int x, int y) {
		presenter.clickedRight(x, y);
	}
}
