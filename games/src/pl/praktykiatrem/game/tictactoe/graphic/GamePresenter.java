package pl.praktykiatrem.game.tictactoe.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.tictactoe.graphic.interfaces.IController;
import pl.praktykiatrem.game.tictactoe.graphic.observers.IGameObserver;
import pl.praktykiatrem.game.tictactoe.graphic.panels.ButtonPanel;
import pl.praktykiatrem.game.tictactoe.graphic.panels.GamePanel;
import pl.praktykiatrem.game.tictactoe.graphic.panels.IView;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class GamePresenter implements IGameObserver {
	private IView gamePanel;
	private ButtonPanel buttonPanel;
	private IController supervisor;
	private JFrame f;

	public GamePresenter(int sizeH, int sizeV, int bSize, IController supervisor) {
		gamePanel = new GamePanel(sizeH, sizeV, bSize, this);
		this.supervisor = supervisor;
	}

	public void showGame() {
		gamePanel.showGame();
	}

	public void closeGame() {
		gamePanel.closeFrame();
	}

	@Override
	public void clicked(int x, int y) {
		supervisor.makeMove(x, y);
	}

	public void changeIcon(Sign sign, int x, int y) {
		if (sign == Sign.O)
			gamePanel.changeIcon(x, y, sign);
		else if (sign == Sign.X)
			gamePanel.changeIcon(x, y, sign);

		disableButton(x, y);
	}

	public void lockGameBoard() {
		gamePanel.lock();
	}

	public void disableButton(int x, int y) {
		gamePanel.disableButton(x, y);
	}

	public void setSignIcon(Sign sign) {
		gamePanel.setSignIcon(sign);
	}

	@Override
	public void abortGame() {
		supervisor.abortGame();
	}

	@Override
	public void buttonClicked(int x, int y) {
		// TODO Auto-generated method stub
	}

}
