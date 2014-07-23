package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ReadyButtonPanel extends JPanel {
	public ReadyButtonPanel() {
		super(new GridLayout(1, 0));
		initialize();
	}

	private void initialize() {
		JButton ready = new JButton("Gotowy!");
		add(ready, BorderLayout.CENTER);
	}

	public Dimension gerPrefferedSize() {
		return new Dimension(150, 150);
	}

	public Dimension gerMinumumSize() {
		return new Dimension(150, 150);
	}
}
