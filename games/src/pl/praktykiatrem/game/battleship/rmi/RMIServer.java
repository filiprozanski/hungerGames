package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnlineServer;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {
	private static final long serialVerisonUID = 1L;
	private ArrayList<HGClient> clients;
	private int connectedClients = 0;
	private Registry r;
	private ArrayList<Integer> freePorts;
	private ArrayList<Integer> portsInUse;
	private String[] names = { "Filip", "Wiktor" };

	public RMIServer(Registry r) throws RemoteException {
		super();
		this.r = r;
		freePorts = new ArrayList<Integer>();
		freePorts.add(9876);
		freePorts.add(9877);
		clients = new ArrayList<HGClient>();
		System.out.println("Server start");
	}

	@Override
	public void showConnection() throws RemoteException {
		System.out.println("Uzyskano po³¹czenie.");
	}

	@Override
	public int logInClient(HGClient client) throws RemoteException {
		int portNumber = freePorts.remove(0);
		portsInUse.add(portNumber);
		client.setPortNumber(portNumber);
		clients.add(client);
		return portNumber;
	}

	@Override
	public void logOutClient(HGClient client) throws RemoteException {
		int portNumber = client.getPortNumber();
		portsInUse.remove(portNumber);
		freePorts.add(portNumber);
		clients.remove(client);
	}

	@Override
	public void startGame() throws RemoteException {
		connectedClients++;
		if (connectedClients == 2) {
			new StartGraphicOnlineServer(names[0], names[1], 1,
					portsInUse.get(0), portsInUse.get(1));
		}
	}
}
