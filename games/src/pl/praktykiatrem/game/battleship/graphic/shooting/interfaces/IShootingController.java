package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public interface IShootingController {

	boolean makeMove(PlayerStatus player, int x, int y) throws RemoteException;

	void resign(PlayerStatus player);

	void callMenu();

	void setHint();
}
