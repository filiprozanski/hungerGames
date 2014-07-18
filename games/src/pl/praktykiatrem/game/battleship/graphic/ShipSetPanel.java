package pl.praktykiatrem.game.battleship.graphic;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.Controller;

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
		JButton seven = new JButton("Siedmiomasztowiec");
		JButton fourA = new JButton("Czteromasztowiec 1");
		JButton fourB = new JButton("Czteromasztowiec 2");
		JButton threeA = new JButton("Trójmasztowiec 1");
		JButton threeB = new JButton("Trójmasztowiec 2");
		JButton twoA = new JButton("Dwumasztowiec 1");
		JButton twoB = new JButton("Dwumasztowiec 2");
		
		add(seven);
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
