package pl.praktykiatrem.game.tictactoe.graphic.panels;

import javax.swing.JPanel;

import pl.praktykiatrem.game.tictactoe.graphic.observers.IButtonPanelObserver;

public class GamePanel extends JPanel {
	private IView boardPanel;
	private ButtonPanel buttonPanel;

	public GamePanel(int sizeH, int sizeV, int bSize,
			IButtonPanelObserver supervisor) {
		boardPanel = new BoardPanel(sizeH, sizeV, bSize, );
		buttonPanel = new ButtonPanel(supervisor);
	}
}
