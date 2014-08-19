package pl.praktykiatrem.game.tictactoe.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaceChoiceListener implements ActionListener {
	private int x;
	private int y;
	private IBoardObserver observer;

	public PlaceChoiceListener(int x, int y, IBoardObserver observer) {
		this.x = x;
		this.y = y;
		this.observer = observer;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		observer.clicked(x, y);
	}
}
