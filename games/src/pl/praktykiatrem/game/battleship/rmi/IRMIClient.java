package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIClient extends Remote {
	public void changeStage() throws RemoteException;
}
