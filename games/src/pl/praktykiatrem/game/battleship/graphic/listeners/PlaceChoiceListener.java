package pl.praktykiatrem.game.battleship.graphic.listeners;

import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;
import pl.praktykiatrem.game.battleship.graphic.events.PlaceChoiceEvent;

public class PlaceChoiceListener {

	public PlaceChoiceListener() {
	}

	public void actionPerformed(PlaceChoiceEvent evt) {
		ShipButton b = evt.getSource();
		int[] tab = evt.getCoords();

		b.setPlaceIcon(1, tab[0], tab[1]);
	}
}
