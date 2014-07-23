package pl.praktykiatrem.game.battleship.graphic.events;

import java.util.EventObject;

import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;

public class PlaceChoiceEvent extends EventObject {
	private int[] coords;
	ShipButton source;

	public PlaceChoiceEvent(Object source) {
		super(source);
		this.source = (ShipButton) source;
		this.coords = this.source.getCoords();
	}

	public int[] getCoords() {
		return coords;
	}

	@Override
	public ShipButton getSource() {
		return (ShipButton) super.getSource();
	}
}
