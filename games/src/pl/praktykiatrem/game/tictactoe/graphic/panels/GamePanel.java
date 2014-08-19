package pl.praktykiatrem.game.tictactoe.graphic.panels;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pl.praktykiatrem.game.tictactoe.graphic.listeners.IBoardObserver;
import pl.praktykiatrem.game.tictactoe.graphic.observers.IGameObserver;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class GamePanel extends JPanel implements IView, IBoardObserver {
	private BoardPanel boardPanel;
	private ButtonPanel buttonPanel;
	private IGameObserver supervisor;
	private JFrame f;

	public GamePanel(int sizeH, int sizeV, int bSize, IGameObserver supervisor) {
		boardPanel = new BoardPanel(sizeH, sizeV, bSize, this);
		buttonPanel = new ButtonPanel(this);
		this.supervisor = supervisor;
		f = new JFrame("Tic Tac Toe");
	}

	public void abortGame() {
		supervisor.abortGame();
	}

	@Override
	public void showGame() {
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

	@Override
	public void clicked(int x, int y) {
		supervisor.clicked(x, y);
	}

	@Override
	public void changeIcon(int x, int y, Sign sign) {
		boardPanel.changeIcon(x, y, sign);
	}

	@Override
	public void lock() {
		boardPanel.lock();
	}

	@Override
	public void disableButton(int x, int y) {
		boardPanel.disableButton(x, y);
	}

	@Override
	public void setSignIcon(Sign sign) {
		buttonPanel.setSignIcon(sign);
	}

	@Override
	public void closeFrame() {
		f.dispose();
	}
}
