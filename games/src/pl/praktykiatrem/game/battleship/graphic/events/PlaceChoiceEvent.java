package pl.praktykiatrem.game.battleship.graphic.events;

import java.awt.event.ActionEvent;

import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;

public class PlaceChoiceEvent extends ActionEvent {
	private int[] coords;
	private ShipButton source;
	private static int id = 1;

	public PlaceChoiceEvent(Object source) {
		super(source, id, "");
		this.source = (ShipButton) source;
		this.coords = this.source.getCoords();
		id++;
	}

	public int[] getCoords() {
		return coords;
	}

	@Override
	public ShipButton getSource() {
		return source;
	}
}
