package pl.praktykiatrem.game.battleship.graphic.buttons;

import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;

public class ShipChoiceButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int id;
	private int polesNumber;

	public ShipChoiceButton(String text, int id, int polesNumber, Icon icon) {
		setModel(new DefaultButtonModel());
		this.id = id;
		this.polesNumber = polesNumber;
		init(text, icon);
	}

	public int getId() {
		return id;
	}

	public int getPolesNumber() {
		return polesNumber;
	}

}