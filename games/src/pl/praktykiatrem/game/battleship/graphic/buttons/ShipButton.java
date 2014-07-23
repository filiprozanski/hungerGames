package pl.praktykiatrem.game.battleship.graphic.buttons;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import pl.praktykiatrem.game.battleship.eventsSample.WybuchListener;
import pl.praktykiatrem.game.battleship.graphic.ShipIcons;
import pl.praktykiatrem.game.battleship.graphic.listeners.PlacingListener;

public class ShipButton extends JButton {
	private int callNumber;
	private boolean shipIsSet;
	private int x;
	private int y;
	private List<PlacingListener> listeneryWyboruMiejsca = new ArrayList<PlacingListener>();

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

	public void setPlaceIcon(int type, int x, int y) {
		ShipIcons.createImages();
		setIcon(ShipIcons.getIcon(type));
	}

	public void addWybuchListener(PlacingListener listener) {
		listeneryWyboruMiejsca.add(listener);
	}

	public void removeWybuchListener(PlacingListener listener) {
		listeneryWyboruMiejsca.remove(listener);
	}

	protected void fireActionPerformed() {
		ActionEvent event = new ActionEvent();
		for (WybuchListener wybuchListener : listeneryWybuchu) {
			wybuchListener.wybuchlo(event);
		}
	}

	public boolean isShipSet() {
		return shipIsSet;
	}

	public void enableShipIsSet() {
		shipIsSet = true;
	}
}
