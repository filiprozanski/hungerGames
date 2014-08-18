package pl.praktykiatrem.game.battleship.graphic.panels;

import javax.swing.JPanel;

import pl.praktykiatrem.game.tictactoe.graphic.observers.IButtonPanelObserver;
import pl.praktykiatrem.game.tictactoe.graphic.panels.BoardPanel;
import pl.praktykiatrem.game.tictactoe.graphic.panels.ButtonPanel;
import pl.praktykiatrem.game.tictactoe.graphic.panels.IView;

public class GamePanel extends JPanel {
	private IView boardPanel;
	private ButtonPanel buttonPanel;

	public GamePanel(int sizeH, int sizeV, int bSize,
			IButtonPanelObserver supervisor) {
		boardPanel = new BoardPanel(sizeH, sizeV, bSize, );
		buttonPanel = new ButtonPanel(supervisor);
	}
}
