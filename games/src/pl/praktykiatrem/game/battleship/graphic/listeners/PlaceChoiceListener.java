package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;

public class PlaceChoiceListener implements MouseListener {
	private int x;
	private int y;
	private IBoardPlaceObserver observer;

	public PlaceChoiceListener(int x, int y, IBoardPlaceObserver observer) {
		this.x = x;
		this.y = y;
		this.observer = observer;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			observer.clickedLeft(x, y);
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			observer.clickedRight(x, y);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}