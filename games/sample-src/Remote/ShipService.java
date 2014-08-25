package Remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import pl.praktykiatrem.game.battleship.components.Ship;

public class ShipService extends UnicastRemoteObject implements ShipInterface {

	private static final long serialVersionUID = 1984282585933975234L;

	public ShipService() throws RemoteException {
		super();
	}

	@Override
	public void helloWorld(String whoSaysHello) {
		System.out.println(whoSaysHello);
	}

	@Override
	public void passShip(Ship s) throws RemoteException {
		System.out.println(s);
		s.setID(6);
	}
}
