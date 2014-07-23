package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.praktykiatrem.game.battleship.graphic.panels.BoardSettingsObserver;

public class PlaceChoiceListener implements ActionListener {
	private int x;
	private int y;
	private BoardSettingsObserver observer;

	public PlaceChoiceListener(int x, int y, BoardSettingsObserver observer) {
		this.x = x;
		this.y = y;
		this.observer = observer;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		observer.clicked(x, y);

	}
}