package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIClient extends Remote {
	public void changeStage() throws RemoteException;

	public void hitSetting(int x, int y, int playerShipsNumber,
			int enemyShipsNumber, int accuracy) throws RemoteException;

	public void missSetting(int x, int y) throws RemoteException;

	public void loseShipSetting(int x, int y) throws RemoteException;

	public void shipSunkSetting(int x, int y) throws RemoteException;
}
