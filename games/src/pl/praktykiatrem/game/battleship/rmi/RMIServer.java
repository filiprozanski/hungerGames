package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {
	private static final long serialVerisonUID = 1L;

	public RMIServer() throws RemoteException {
		super();
		System.out.println("Server start");
	}

	@Override
	public void zshowConnection() throws RemoteException {
		System.out.println("Uzyskano po³¹czenie.");
	}

}
