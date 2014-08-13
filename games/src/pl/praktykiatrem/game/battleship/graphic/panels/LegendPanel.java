package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;

/**
 * Panel zawieraj¹cy legendê
 * 
 * @author Filip Ró¿añski
 *
 */
public class LegendPanel extends JPanel {

	private static final long serialVersionUID = -5110374745603365105L;

	public LegendPanel() {
		super(new GridLayout(3, 0));
		setBackground(new Color(135, 206, 235));
		initialize();
	}

	private void initialize() {
		ShipIcons.createImages();
		JPanel shipsLeft = new JPanel(new GridLayout(2, 0));
		JPanel legend1 = new JPanel(new GridLayout(3, 0));
		JPanel legend2 = new JPanel(new GridLayout(3, 0));

		shipsLeft.setBackground(new Color(135, 206, 235));
		legend1.setBackground(new Color(135, 206, 235));
		legend2.setBackground(new Color(135, 206, 235));

		// shipsLeft.add(new JLabel("Tutaj bêd± statki przeciwnika"));
		// shipsLeft.add(new JLabel("Tutaj bêd± statki przeciwnika"));
		legend1.add(new JLabel("Legenda:", JLabel.CENTER));
		legend1.add(new JLabel("Statek", ShipIcons.getShipIcon(1),
				JLabel.CENTER));

		legend1.add(new JLabel("Ustawiony Statek", ShipIcons.getOkIcon(2, 1,
				null), JLabel.CENTER));

		legend2.add(new JLabel("Zatopiony Statek", ShipIcons.getStateIcon(0),
				JLabel.CENTER));

		legend2.add(new JLabel("Pud³o", ShipIcons.getStateIcon(1),
				JLabel.CENTER));

		legend2.add(new JLabel("Trafiony Statek", ShipIcons.getStateIcon(2),
				JLabel.CENTER));

		add(shipsLeft);
		add(legend1);
		add(legend2);

	}

	public Dimension getPrefferedSize() {
		return new Dimension(150, 150);
	}

	public Dimension getMinumumSize() {
		return new Dimension(150, 150);
	}

}