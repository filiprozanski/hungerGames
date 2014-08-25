package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;

public interface IShootingController {

	boolean makeMove(PlayerStatus player, Coordinates coords)
			throws RemoteException;

	void resign(PlayerStatus player);

	void endGame();

	void setHint();
}
