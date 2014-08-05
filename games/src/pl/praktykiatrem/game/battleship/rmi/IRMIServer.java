package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIServer extends Remote {
	public void zshowConnection() throws RemoteException;
}
