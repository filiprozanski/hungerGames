package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnline;
import pl.praktykiatrem.game.battleship.rules.Game;

public class RMIClient extends UnicastRemoteObject implements IRMIClient {
	private Registry r;

	private IRMIServer server;

	private StartGraphicOnline starter;

	public RMIClient(StartGraphicOnline starter) throws RemoteException {
		super();

		this.starter = starter;

		try {
			r = LocateRegistry.getRegistry("localhost", IRMIServer.PORTNUMBER);
		} catch (RemoteException e) {
			System.out.println("getRegistry");
			e.printStackTrace();
		}
		try {
			server = (IRMIServer) r.lookup(IRMIServer.SERVICE_NAME);
		} catch (RemoteException | NotBoundException e) {
			System.out.println("lookup");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1921402231416408470L;

	@Override
	public void changeStage() throws RemoteException {
		starter.changeStage();
	}

	public Game getGame() throws RemoteException {
		return server.getGame();
	}

}
