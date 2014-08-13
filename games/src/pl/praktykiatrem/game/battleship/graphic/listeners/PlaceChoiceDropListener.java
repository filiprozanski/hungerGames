package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JButton;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;

public class PlaceChoiceDropListener extends DropTargetAdapter {

	private DropTarget dropTarget;
	private JButton button;
	private int x;
	private int y;
	private IBoardPlaceObserver observer;

	public PlaceChoiceDropListener(JButton button, int x, int y,
			IBoardPlaceObserver observer) {
		this.button = button;
		this.x = x;
		this.y = y;
		this.observer = observer;

		dropTarget = new DropTarget(button, DnDConstants.ACTION_COPY, this,
				true, null);
	}

	public void drop(DropTargetDropEvent event) {
		try {
			Transferable tr = event.getTransferable();
			int id = (int) tr.getTransferData(ListenerClipboard.idFlavor);
			Direction dir = (Direction) tr
					.getTransferData(ListenerClipboard.dirFlavor);
			if (event.isDataFlavorSupported(ListenerClipboard.idFlavor)) {
				event.acceptDrop(DnDConstants.ACTION_COPY);
				observer.dropShip(id, x, y, dir);
				event.dropComplete(true);
				return;
			}
			event.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			event.rejectDrop();
		}
	}
}
