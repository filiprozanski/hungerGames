package pl.praktykiatrem.game.battleship.graphic.buttons;

import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.graphic.listeners.ShipChoiceDragListener;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;

/**
 * Klasa opisuje przycisk bêd¹cy graficzn¹ reprezentacj¹ pola gry na planszy
 * 
 * @author Filip Ró¿añski
 *
 */
public class ShipButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int id = -1;
	private int x;
	private int y;

	public ShipButton(String text, int id, Icon icon,
			IBoardPlaceObserver observer) {
		setModel(new DefaultButtonModel());
		this.id = id;
		init(text, icon);
		new ShipChoiceDragListener(this, observer);
	}

	public ShipButton(int x, int y, boolean dragable,
			IBoardPlaceObserver observer) {
		this.x = x;
		this.y = y;
		if (dragable)
			new ShipChoiceDragListener(this, observer);
	}

	public ShipButton(boolean b) {
	}

	public int getId() {
		return id;
	}

	public Coordinates gerCoords() {
		return new Coordinates(x, y);
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
