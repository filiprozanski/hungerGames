package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.Controller;
import pl.praktykiatrem.game.battleship.graphic.listeners.ShipChoiceListener;

public class ShipSetPanel extends JPanel {
	private final int rowsNumber = 7;
	Controller control;
	
	public ShipSetPanel (Controller control)
	{
		super(new GridLayout(7, 0));
		
		this.control = control;
		initialize();
	}
	
	private void initialize()
	{
		JButton six = new JButton("Szeœciomasztowiec");
		JButton fourA = new JButton("Czteromasztowiec 1");
		JButton fourB = new JButton("Czteromasztowiec 2");
		JButton threeA = new JButton("Trójmasztowiec 1");
		JButton threeB = new JButton("Trójmasztowiec 2");
		JButton twoA = new JButton("Dwumasztowiec 1");
		JButton twoB = new JButton("Dwumasztowiec 2");
		
		six.addActionListener(new ShipChoiceListener(6));
		fourA.addActionListener(new ShipChoiceListener(4));
		fourB.addActionListener(new ShipChoiceListener(4));
		threeA.addActionListener(new ShipChoiceListener(3));
		threeB.addActionListener(new ShipChoiceListener(3));
		twoA.addActionListener(new ShipChoiceListener(2));
		twoB.addActionListener(new ShipChoiceListener(2));
		
		add(six);
		add(fourA);
		add(fourB);
		add(threeA);
		add(threeB);
		add(twoA);
		add(twoB);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(330, 330);
	}
	
	public Dimension getMinimumSize()
	{
		return new Dimension(330, 330);
	}
}
