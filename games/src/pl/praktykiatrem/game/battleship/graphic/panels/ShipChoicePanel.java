package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.listeners.ShipChoiceListener;
import pl.praktykiatrem.game.battleship.graphic.observers.IShipChoiceObserver;

public class ShipChoicePanel extends JPanel {
    private final int rowsNumber = 7;
    private IShipChoiceObserver observer;
    private ArrayList<JButton> ships;

    public ShipChoicePanel(IShipChoiceObserver observer, int[] shipsTypes) {
	super(new GridLayout(7, 0));
	ships = new ArrayList<JButton>();
	this.observer = observer;
	initialize(shipsTypes);
    }

    private void initialize(int[] shipTypes) {
	for (int i = 0; i < shipTypes.length; i++) {
	    ships.add(new JButton("Liczba masztow: " + shipTypes[i]));
	    ships.get(i).addActionListener(
		    new ShipChoiceListener(shipTypes[i], i, observer));
	}

	for (JButton button : ships)
	    add(button);
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(330, 330);
    }

    @Override
    public Dimension getMinimumSize() {
	return new Dimension(330, 330);
    }

    public void enableShipButton(int id) {
	ships.get(id).setEnabled(true);
    }

    public void disableShipButton(int id) {
	ships.get(id).setEnabled(false);
    }
}