package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.Controller;

public class ReadyPanel extends JPanel {
	Controller control;
	
	public ReadyPanel(Controller control)
	{
		super(new GridLayout(1, 0));
		this.control = control;
		initialize();
	}

	private void initialize() {
		JButton ready = new JButton("Gotowy!");
		add(ready, BorderLayout.CENTER);
	}
	
	public Dimension gerPrefferedSize()
	{
		return new Dimension(150, 150);
	}
	
	public Dimension gerMinumumSize()
	{
		return new Dimension(150, 150);
	}
}
