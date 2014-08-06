package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIServer extends Remote {
	public void showConnection() throws RemoteException;

	public void logInClient(HGClient client) throws RemoteException;

	public void logOutClient(HGClient client) throws RemoteException;
}
