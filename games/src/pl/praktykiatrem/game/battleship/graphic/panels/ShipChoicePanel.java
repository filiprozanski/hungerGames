package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.listeners.ShipChoiceListener;

public class ShipChoicePanel extends JPanel {
	private final int rowsNumber = 7;
	private IShipChoiceObserver observer;

	public ShipChoicePanel(IShipChoiceObserver observer) {
		super(new GridLayout(7, 0));
		this.observer = observer;
		initialize();
	}

	private void initialize() {
		JButton six = new JButton("Szesciomasztowiec");
		JButton fourA = new JButton("Czteromasztowiec 1");
		JButton fourB = new JButton("Czteromasztowiec 2");
		JButton threeA = new JButton("Trójmasztowiec 1");
		JButton threeB = new JButton("Trójmasztowiec 2");
		JButton twoA = new JButton("Dwumasztowiec 1");
		JButton twoB = new JButton("Dwumasztowiec 2");

		six.addActionListener(new ShipChoiceListener(6, 0, observer));
		fourA.addActionListener(new ShipChoiceListener(4, 1, observer));
		fourB.addActionListener(new ShipChoiceListener(4, 2, observer));
		threeA.addActionListener(new ShipChoiceListener(3, 3, observer));
		threeB.addActionListener(new ShipChoiceListener(3, 4, observer));
		twoA.addActionListener(new ShipChoiceListener(2, 5, observer));
		twoB.addActionListener(new ShipChoiceListener(2, 6, observer));

		add(six);
		add(fourA);
		add(fourB);
		add(threeA);
		add(threeB);
		add(twoA);
		add(twoB);
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