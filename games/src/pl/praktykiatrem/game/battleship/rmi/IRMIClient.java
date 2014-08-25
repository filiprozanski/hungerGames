package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.Place;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;

public interface IRMIClient extends Remote {
	public void changeStage(boolean start) throws RemoteException;

	public void hitSetting(Coordinates coords, int playerShipsNumber,
			int enemyShipsNumber, int accuracy) throws RemoteException;

	public void missSetting(int playerShips, Coordinates coords,
			int enemyShips, int accuracy) throws RemoteException;

	public void losePoleSetting(Coordinates coords, int pNumber, int eNumber)
			throws RemoteException;

	public void shipSunkSetting(Coordinates[] list, int id, int eNumber,
			int pNumber, int accuracy) throws RemoteException;

	public void allowToMove(Coordinates coords) throws RemoteException;

	void gameOver(boolean isWinner) throws RemoteException;

	void resign(PlayerStatus player) throws RemoteException;

	public void drawLeftShips(ArrayList<Place> leftShips)
			throws RemoteException;
}
