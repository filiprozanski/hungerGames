package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;
import pl.praktykiatrem.game.battleship.graphic.panels.IBoardSettingsObserver;

public class PlaceChoiceListener implements ActionListener {
	private int x;
	private int y;
	private int freq;
	private IBoardSettingsObserver observer;

	public PlaceChoiceListener(int x, int y, IBoardSettingsObserver observer) {
		this.x = x;
		this.y = y;
		this.observer = observer;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		ShipButton b = (ShipButton) evt.getSource();
		b.changeCallNumber();
		freq = b.getCallNumber();
		observer.clicked(x, y, freq);
	}
}