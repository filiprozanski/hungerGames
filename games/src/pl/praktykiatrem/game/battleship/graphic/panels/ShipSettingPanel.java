package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

<<<<<<< HEAD
import pl.praktykiatrem.game.battleship.graphic.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.ISettingView;

public class ShipSettingPanel extends JPanel implements IBoardSettingsObserver,
		ISettingView {
=======
import pl.praktykiatrem.game.battleship.graphic.IBasicPresenter;

public class ShipSettingPanel extends JPanel implements IBoardSettingsObserver {
>>>>>>> refs/remotes/origin/Wiktor
	private BoardGraphicSettingPanel boardPanel;
	private ShipChoicePanel choicePanel;
	private ReadyButtonPanel readyPanel;
<<<<<<< HEAD
	private ISettingPresenter presenter;
=======
	private IBasicPresenter presenter;
>>>>>>> refs/remotes/origin/Wiktor

<<<<<<< HEAD
	public ShipSettingPanel(ISettingPresenter presenter) {
=======
	public ShipSettingPanel(IBasicPresenter presenter) {
>>>>>>> refs/remotes/origin/Wiktor
		this.presenter = presenter;
	}

	private void initialize() {
		setLayout(new GridLayout(2, 2));
		boardPanel = new BoardGraphicSettingPanel();
		boardPanel.setBoardSettingsObserver(this);
		choicePanel = new ShipChoicePanel();
		readyPanel = new ReadyButtonPanel();

		add(boardPanel);
		add(choicePanel);
		add(readyPanel);
	}

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

	@Override
	public void clicked(int x, int y, int freq) {
		presenter.placeShip(x, y, freq);
	}
}
