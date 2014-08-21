package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

public interface IRMIClient extends Remote {
	public void changeStage(boolean start) throws RemoteException;

	public void hitSetting(int x, int y, int playerShipsNumber,
			int enemyShipsNumber, int accuracy) throws RemoteException;

	public void missSetting(int playerShips, int x, int y, int enemyShips,
			int accuracy) throws RemoteException;

	public void losePoleSetting(int x, int y, int pNumber, int eNumber)
			throws RemoteException;

	public void shipSunkSetting(Coordinates[] list, int id)
			throws RemoteException;

	public void allowToMove(int x, int y) throws RemoteException;
}
