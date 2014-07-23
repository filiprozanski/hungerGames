package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ShipSettingPanel extends JPanel implements BoardSettingsObserver {
	private BoardGraphicSettingPanel boardPanel;
	private ShipChoicePanel choicePanel;
	private ReadyButtonPanel readyPanel;

	public ShipSettingPanel() {
		setLayout(new GridLayout(2, 2));
		boardPanel = new BoardGraphicSettingPanel();
		boardPanel.setBoardSettingsObserver(this);
		choicePanel = new ShipChoicePanel();
		readyPanel = new ReadyButtonPanel();

		add(boardPanel);
		add(choicePanel);
		add(readyPanel);
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
	public void clicked(int x, int y) {

	}
}
