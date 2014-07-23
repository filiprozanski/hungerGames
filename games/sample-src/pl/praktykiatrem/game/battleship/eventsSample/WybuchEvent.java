package pl.praktykiatrem.game.battleship.eventsSample;

import java.util.EventObject;

public class WybuchEvent extends EventObject {

	private int poziomZniszczen;

	public WybuchEvent(Object source, int poziomZniszczen) {
		super(source);
		this.poziomZniszczen = poziomZniszczen;
	}

	public int getPoziomZniszczen() {
		return poziomZniszczen;
	}

}
