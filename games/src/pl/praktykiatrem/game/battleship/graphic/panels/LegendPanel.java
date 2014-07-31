package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;

public class LegendPanel extends JPanel {

	public LegendPanel() {
		super(new GridLayout(10, 0));
		setBackground(new Color(135, 206, 235));
		initialize();

	}

	private void initialize() {
		ShipIcons.createImages();
		add(new JLabel("Legenda:", JLabel.CENTER));
		add(new JLabel("Statek", ShipIcons.getIcon(1), JLabel.CENTER));

		add(new JLabel("Ustawiony Statek", ShipIcons.getOkIcon(2),
				JLabel.CENTER));

		add(new JLabel("Zatopiony Statek", ShipIcons.getIcon(8), JLabel.CENTER));

		add(new JLabel("Pud�o", ShipIcons.getIcon(9), JLabel.CENTER));

		add(new JLabel("Trafiony Statek", ShipIcons.getIcon(10), JLabel.CENTER));

	}

	public Dimension getPrefferedSize() {
		return new Dimension(150, 150);
	}

	public Dimension getMinumumSize() {
		return new Dimension(150, 150);
	}

}