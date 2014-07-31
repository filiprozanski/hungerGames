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
		super(new GridLayout(10, 0));
		setBackground(new Color(135, 206, 235));
		initialize();
	}

	private void initialize() {
		ShipIcons.createImages();
		// statusLabel.setIcon(ready_icon);

		add(new JLabel("Legenda:", JLabel.CENTER));
		add(new JLabel("Statek", ShipIcons.getShipIcon(1), JLabel.CENTER));

		add(new JLabel("Ustawiony Statek", ShipIcons.getOkIcon(2),
				JLabel.CENTER));

		add(new JLabel("Zatopiony Statek", ShipIcons.getStateIcon(0),
				JLabel.CENTER));

		add(new JLabel("Pud³o", ShipIcons.getStateIcon(1), JLabel.CENTER));

		add(new JLabel("Trafiony Statek", ShipIcons.getStateIcon(2),
				JLabel.CENTER));

	}

	public Dimension getPrefferedSize() {
		return new Dimension(150, 150);
	}

	public Dimension getMinumumSize() {
		return new Dimension(150, 150);
	}

}