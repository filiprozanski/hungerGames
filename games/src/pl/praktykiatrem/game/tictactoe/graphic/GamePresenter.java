package pl.praktykiatrem.game.tictactoe.graphic;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class GamePresenter implements IBoardPlaceObserver, IButtonPanelObserver {
	private BoardPanel boardPanel;
	private ButtonPanel buttonPanel;
	private IController supervisor;
	private JFrame f;

	public GamePresenter(int sizeH, int sizeV, int bSize, IController supervisor) {
		boardPanel = new BoardPanel(sizeH, sizeV, bSize, this);
		buttonPanel = new ButtonPanel(this);
		this.supervisor = supervisor;
	}

	public void showGame() {
		f = new JFrame("Tic Tac Toe");

		f.getContentPane().setLayout(null);
		f.getContentPane().add(boardPanel);
		f.getContentPane().add(buttonPanel);

		Insets inset = f.getContentPane().getInsets();
		Dimension size = boardPanel.getPreferredSize();
		boardPanel.setBounds(0, 0, size.width, size.height);

		Dimension size2 = buttonPanel.getPreferredSize();
		int posX = size.width / 2 - size2.width / 2;
		buttonPanel.setBounds(posX, size.height, size2.width, size2.height);

		f.setSize(size.width + 15, size.height + size2.height + 37);
		// f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void closeGame() {
		f.dispose();
	}

	@Override
	public void clicked(int x, int y, int freq) {
		supervisor.makeMove(x, y);
	}

	public void changeIcon(Sign sign, int x, int y) {
		if (sign == Sign.O)
			boardPanel.changeIcon(x, y, sign);
		else if (sign == Sign.X)
			boardPanel.changeIcon(x, y, sign);

		disableButton(x, y);
	}

	public void lockGameBoard() {
		boardPanel.lock();
	}

	public void disableButton(int x, int y) {
		boardPanel.disableButton(x, y);
	}

	public void setSignIcon(Sign sign) {
		buttonPanel.setSignIcon(sign);
	}

	@Override
	public void abortGame() {
		supervisor.abortGame();
	}

}
