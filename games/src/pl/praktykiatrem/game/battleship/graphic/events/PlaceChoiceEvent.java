package pl.praktykiatrem.game.battleship.graphic.events;

import java.util.EventObject;

public class PlaceChoiceEvent extends EventObject {

	private int x;
	private int y;

	public PlaceChoiceEvent(Object source, int x, int y) {
		super(source);
		this.x = x;
		this.y = y;
	}

	public int[] getCords() {
		int[] coords = { x.y };
		return;
	}
}
