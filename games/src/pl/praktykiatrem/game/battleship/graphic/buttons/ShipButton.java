package pl.praktykiatrem.game.battleship.graphic.buttons;

import javax.swing.JButton;

import pl.praktykiatrem.game.battleship.files.ShipIcons;

/**
 * Klasa opisuje przycisk b�d�cy graficzn� reprezentacj� pola gry na planszy
 * 
 * @author Filip R�a�ski
 *
 */
public class ShipButton extends JButton {
	private static final long serialVersionUID = 4064035001395922627L;
	private int callNumber = 1;

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
		if (type == 0) {
			setIcon(null);
			setDisabledIcon(null);
		} else {
			setIcon(ShipIcons.getShipIcon(type));
			setDisabledIcon(ShipIcons.getShipIcon(type));
		}
	}

	public void setPlaceStateIcon(int type) {
		ShipIcons.createImages();
		setIcon(ShipIcons.getStateIcon(type));
		setDisabledIcon(ShipIcons.getStateIcon(type));
	}
}
