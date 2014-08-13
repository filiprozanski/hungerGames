package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import pl.praktykiatrem.game.uniElements.enums.Direction;

class TransferableId implements Transferable {

	protected static DataFlavor idFlavor = new DataFlavor(int.class,
			"A Int Object");
	protected static DataFlavor dirFlavor = new DataFlavor(Direction.class,
			"A Direction Object");

	protected static DataFlavor[] supportedFlavors = { idFlavor, dirFlavor,
			DataFlavor.stringFlavor, };
	int id;
	Direction dir;

	public TransferableId(int id, Direction dir) {
		this.id = id;
		this.dir = dir;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (flavor.equals(idFlavor) || flavor.equals(dirFlavor)
				|| flavor.equals(DataFlavor.stringFlavor))
			return true;
		return false;
	}

	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException {
		if (flavor.equals(idFlavor))
			return id;
		else if (flavor.equals(dirFlavor))
			return dir;
		else if (flavor.equals(DataFlavor.stringFlavor))
			return id;
		else
			throw new UnsupportedFlavorException(flavor);
	}
}
