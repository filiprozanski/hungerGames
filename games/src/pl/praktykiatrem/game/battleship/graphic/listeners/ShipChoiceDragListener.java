package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;

public class ShipChoiceDragListener implements DragGestureListener {
	IBoardPlaceObserver observer;

	public ShipChoiceDragListener(ShipButton button,
			IBoardPlaceObserver observer) {
		this.observer = observer;
		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(button, DnDConstants.ACTION_COPY,
				this);
	}

	public void dragGestureRecognized(DragGestureEvent event) {
		ShipButton button = (ShipButton) event.getComponent();
		int id = button.getId();
		int polesNumber = -1;
		Direction dir = Direction.HORIZONTAL;
		if (id < 0)
			id = observer.getID(button.gerCoords().getX(), button.gerCoords()
					.getY());

		if (id >= 0) {
			polesNumber = observer.getPolesNumber(id);
			if (observer.getDirection(id) != null)
				dir = observer.getDirection(id);
		}
		if (polesNumber > 0)
			event.startDrag(null,
					ShipIcons.getShipImage(id + 1, polesNumber, dir), // dodaæ
																		// dir
																		// do
																		// ikon
					new Point(15, 15), new TransferableId(id, dir), null);
	}
}
