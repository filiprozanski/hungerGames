package Remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.Ship;

public interface ShipInterface extends Remote {
	public void helloWorld(String whoSaysHello) throws RemoteException;

	public void passShip(Ship s) throws RemoteException;
}
