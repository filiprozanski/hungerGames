package pl.praktykiatrem.game.battleship.eventsSample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorZdarzen {

	private List<WybuchListener> listeneryWybuchu = new ArrayList<WybuchListener>();

	public void wykonajEksperyment() {

		//
		//
		if (true) {// eksperyment sie nie powiodl {
			fireWybuch(new Random().nextInt() % 10);
		}
	}

	public void addWybuchListener(WybuchListener listener) {
		listeneryWybuchu.add(listener);

	}

	public void removeWybuchListener(WybuchListener listener) {
		listeneryWybuchu.remove(listener);
	}

	protected void fireWybuch(int poziomZniszczen) {
		WybuchEvent event = new WybuchEvent(this, poziomZniszczen);
		for (WybuchListener wybuchListener : listeneryWybuchu) {
			wybuchListener.wybuchlo(event);
		}
	}

}
