package pl.praktykiatrem.game.battleship.graphic.buttons;

import javax.swing.JButton;

import pl.praktykiatrem.game.battleship.graphic.ShipIcons;

public class ShipButton extends JButton {
	private int callNumber;
	private boolean shipIsSet;
	private int x;
	private int y;

	public ShipButton() {
		shipIsSet = false;
	}

	public int getCallNumber() {
		return callNumber;
	}

	public void changeCallNumber() {
		if (callNumber == 2)
			callNumber = 0;
		else
			callNumber = callNumber + 1;
	}

	public void setPlaceIcon(int type) {
		ShipIcons.createImages();
		setIcon(ShipIcons.getIcon(type));
	}

	/*
	 * public int[] getCoords() { int[] tab = { x, y }; return tab; }
	 * 
	 * public boolean isShipSet() { return shipIsSet; }
	 * 
	 * public void enableShipIsSet() { shipIsSet = true; }
	 */
}
