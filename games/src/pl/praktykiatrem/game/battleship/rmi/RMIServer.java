package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {
	private static final long serialVerisonUID = 1L;
	private ArrayList<HGClient> clients;

	public RMIServer() throws RemoteException {
		super();
		clients = new ArrayList<HGClient>();
		System.out.println("Server start");
	}

	@Override
	public void showConnection() throws RemoteException {
		System.out.println("Uzyskano po³¹czenie.");
	}

	@Override
	public void logInClient(HGClient client) throws RemoteException {
		clients.add(client);
	}

	@Override
	public void logOutClient(HGClient client) throws RemoteException {
		clients.remove(client);
	}

}
