package pl.praktykiatrem.game.battleship.graphic.buttons;

import javax.swing.JButton;

import pl.praktykiatrem.game.battleship.files.ShipIcons;

public class ShipButton extends JButton {
	private int callNumber = 1;
	private boolean shipIsSet;

	public ShipButton() {
		shipIsSet = false;
	}

	public int getCallNumber() {
		return callNumber;
	}

	public void changeCallNumber(int number) {
		if (number == 2)
			callNumber = 2;
		else if (number == 1)
			callNumber = 1;
		else
			callNumber = 0;
	}

	public void setPlaceIcon(int type) {
		ShipIcons.createImages();
		setIcon(ShipIcons.getIcon(type));
		setDisabledIcon(ShipIcons.getIcon(type));
	}

	/*
	 * public int[] getCoords() { int[] tab = { x, y }; return tab; }
	 * 
	 * public boolean isShipSet() { return shipIsSet; }
	 * 
	 * public void enableShipIsSet() { shipIsSet = true; }
	 */
}
