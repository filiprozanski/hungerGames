package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import pl.praktykiatrem.game.battleship.graphic.observers.IShipChoiceObserver;

public class ShipChoiceListener implements ActionListener{
	private int polesNumber;
	private int id;
	private IShipChoiceObserver observer;
	
	public ShipChoiceListener(int p, int id, IShipChoiceObserver observer)
	{
		this.observer = observer;
		this.id = id;
		polesNumber = p;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		JButton source = (JButton) evt.getSource();
		observer.choiceDone(id, polesNumber);
	}
}
