package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ShipChoiceListener implements ActionListener{
	private int polesNumber;
	
	public ShipChoiceListener(int p)
	{
		polesNumber = p;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		JButton source = (JButton) evt.getSource();
		source.setEnabled(false);
	}
}
