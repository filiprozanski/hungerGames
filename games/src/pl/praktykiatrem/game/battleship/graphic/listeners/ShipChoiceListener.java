package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.praktykiatrem.game.battleship.graphic.observers.IShipChoiceObserver;

/**
 * listener nasluchuj¹cy, czy u¿ytkownik dokona³ wyboru statku, który chce
 * ustawiæ
 * 
 * @author Filip Ró¿añski
 *
 */
public class ShipChoiceListener implements ActionListener {
	private int polesNumber;
	private int id;
	private IShipChoiceObserver observer;

	public ShipChoiceListener(int p, int id, IShipChoiceObserver observer) {
		this.observer = observer;
		this.id = id;
		polesNumber = p;
	}

	public void actionPerformed(ActionEvent evt) {
		observer.choiceDone(id, polesNumber);
	}
}
