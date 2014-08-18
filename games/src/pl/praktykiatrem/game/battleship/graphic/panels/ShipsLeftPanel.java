package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;

/**
 * Panel zawieraj�cy legend�
 * 
 * @author Filip R�a�ski
 *
 */
public class ShipsLeftPanel extends JPanel {

	private static final long serialVersionUID = -5110374745603365105L;
	private JLabel[] ships;
	private int[] shipTypes;

	public ShipsLeftPanel(int[] shipTypes) {
		super(new GridLayout(3, 0));
		this.shipTypes = new int[shipTypes.length];

		for (int i = 0; i < shipTypes.length; i++)
			this.shipTypes[i] = shipTypes[i];

		ships = new JLabel[shipTypes.length];
		setBackground(new Color(135, 206, 235));
		initialize();
	}

	private void initialize() {
		ShipIcons.createImages();
		// ShipIcons.createColors(shipTypes.length);
		JPanel shipsLeft = new JPanel();
		JPanel legend1 = new JPanel(new GridLayout(3, 0));
		JPanel legend2 = new JPanel(new GridLayout(3, 0));

		shipsLeft.setBackground(new Color(135, 206, 235));
		legend1.setBackground(new Color(135, 206, 235));
		legend2.setBackground(new Color(135, 206, 235));

		for (int i = 0; i < shipTypes.length; i++) {
			ships[i] = new JLabel(ShipIcons.getShipIcon(i + 1, shipTypes[i],
					null));
			shipsLeft.add(ships[i]);
		}

		legend1.add(new JLabel("Legenda:", JLabel.CENTER));
		legend1.add(new JLabel("Statek", ShipIcons.getShipIcon(1),
				JLabel.CENTER));

		legend1.add(new JLabel("Ustawiony Statek", ShipIcons.getOkIcon(2, 1,
				null), JLabel.CENTER));

		legend2.add(new JLabel("Zatopiony Statek", ShipIcons.getStateIcon(0),
				JLabel.CENTER));

		legend2.add(new JLabel("Pud�o", ShipIcons.getStateIcon(1),
				JLabel.CENTER));

		legend2.add(new JLabel("Trafiony Statek", ShipIcons.getStateIcon(2),
				JLabel.CENTER));

		add(shipsLeft);
		add(legend1);
		add(legend2);

	}

	public void changeShipIcon(int shipID) {
		ships[shipID]
				.setIcon(ShipIcons.getShipIcon(0, shipTypes[shipID], null));
	}

	public Dimension getPrefferedSize() {
		return new Dimension(150, 150);
	}

	public Dimension getMinumumSize() {
		return new Dimension(150, 150);
	}

}