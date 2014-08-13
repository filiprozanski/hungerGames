package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
import pl.praktykiatrem.game.uniElements.enums.Direction;
import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;

/**
 * Panel zawieraj¹cy dostêpne statki
 * 
 * @author Filip Ró¿añski
 *
 */
public class ShipChoicePanel extends JPanel {
	private static final long serialVersionUID = -5010598464397087691L;

	private IBoardPlaceObserver observer;

	private ArrayList<ShipButton> ships;
	private int[] shipTypes;

	public ShipChoicePanel(IBoardPlaceObserver observer, int[] shipsTypes) {
		super(new GridLayout(shipsTypes.length, 0));
		ships = new ArrayList<ShipButton>();
		this.observer = observer;
		this.shipTypes = shipsTypes;
		initialize(shipsTypes);
	}

	private void initialize(int[] shipTypes) {
		ShipIcons.createImages();
		ShipIcons.createColors(shipTypes.length);
		for (int i = 0; i < shipTypes.length; i++) {
			ships.add(new ShipButton(" ", i, ShipIcons.getShipIcon(i + 1,
					shipTypes[i], Direction.HORIZONTAL), observer));
			ships.get(i).setContentAreaFilled(false);
		}
		setBackground(new Color(135, 206, 235));

		for (ShipButton button : ships)
			add(button);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(330, 330);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(330, 330);
	}

	public void setOkIconShipButton(int id, boolean ok) {
		if (ok == true) {
			ships.get(id).setIcon(ShipIcons.getOkIcon(id + 1, 1, null));
			ships.get(id)
					.setText("Ustawi³e¶ " + shipTypes[id] + " masztowiec.");
		} else {
			ships.get(id).setIcon(
					ShipIcons.getShipIcon(id + 1, shipTypes[id], null));
			ships.get(id).setText("");
		}

	}

	public void enableShipButton(int id) {
		ships.get(id).setEnabled(true);
	}

	public void disableShipButton(int id) {
		ships.get(id).setEnabled(false);
	}
}
