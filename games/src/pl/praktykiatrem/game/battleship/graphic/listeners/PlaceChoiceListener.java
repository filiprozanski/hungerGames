package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;

public class PlaceChoiceListener implements ActionListener {

	public PlaceChoiceListener() {
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		ShipButton b = (ShipButton) evt.getSource();

		b.setPlaceIcon(1);
	}
}
