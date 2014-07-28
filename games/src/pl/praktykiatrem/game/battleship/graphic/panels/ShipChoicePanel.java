package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.listeners.ShipChoiceListener;

public class ShipChoicePanel extends JPanel {
	private final int rowsNumber = 7;
	private IShipChoiceObserver observer;
	private ArrayList<JButton> ships;

	public ShipChoicePanel(IShipChoiceObserver observer) {
		super(new GridLayout(7, 0));
		ships = new ArrayList<JButton>();
		this.observer = observer;
		initialize();
	}

	private void initialize() {
		ships.add(new JButton("Szesciomasztowiec"));
		ships.add(new JButton("Czteromasztowiec 1"));
		ships.add(new JButton("Czteromasztowiec 2"));
		ships.add(new JButton("Trójmasztowiec 1"));
		ships.add(new JButton("Trójmasztowiec 2"));
		ships.add(new JButton("Dwumasztowiec 1"));
		ships.add(new JButton("Dwumasztowiec 2"));

		ships.get(0).addActionListener(new ShipChoiceListener(6, 0, observer));
		ships.get(1).addActionListener(new ShipChoiceListener(4, 1, observer));
		ships.get(2).addActionListener(new ShipChoiceListener(4, 2, observer));
		ships.get(3).addActionListener(new ShipChoiceListener(3, 3, observer));
		ships.get(4).addActionListener(new ShipChoiceListener(3, 4, observer));
		ships.get(5).addActionListener(new ShipChoiceListener(2, 5, observer));
		ships.get(6).addActionListener(new ShipChoiceListener(2, 6, observer));

		for (JButton button : ships)
			add(button);
		
		/*add(six);
		add(fourA);
		add(fourB);
		add(threeA);
		add(threeB);
		add(twoA);
		add(twoB);*/
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(330, 330);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(330, 330);
	}
}