package pl.praktykiatrem.game.tictactoe.graphic;

import java.awt.GridLayout;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class GamePresenter implements IBoardPlaceObserver {
	private BoardPanel boardPanel;
	private ButtonPanel buttonPanel;
	private IController supervisor;

	public GamePresenter(int sizeH, int sizeV, IController supervisor) {
		boardPanel = new BoardPanel(sizeH, sizeV, this);
		buttonPanel = new ButtonPanel();
		this.supervisor = supervisor;
	}

	public void showGame() {
		JFrame f = new JFrame("Tic Tac Toe");
		f.getContentPane().setLayout(new GridLayout(2, 0));
		f.getContentPane().add(boardPanel);
		f.getContentPane().add(buttonPanel);
		f.setSize(300, 400);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	@Override
	public void clicked(int x, int y, int freq) {
		supervisor.makeMove(x, y);
	}

	public void changeIcon(Sign sign, int x, int y) {
		if (sign == Sign.O)
			boardPanel.changeIcon(x, y, 0);
		else if (sign == Sign.X)
			boardPanel.changeIcon(x, y, 1);
	}

	public void lockGameBoard() {
		boardPanel.lock();
	}

}
