package Remote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import pl.praktykiatrem.game.battleship.gameComponents.Ship;

public class Client {
	public static void main(String[] args) throws RemoteException,
			NotBoundException {
		Registry r = LocateRegistry.getRegistry("localhost", 9875);
		ShipInterface s = (ShipInterface) r.lookup("ShipService");
		System.out.println("Jestem klientem");
		System.out.println("wywo³uje metode");
		s.helloWorld("Wiktor");
		System.out.println("wywo³a³em!");
		Ship ship = new Ship(5);
		ship.setID(6);
		s.passShip(ship);
	}
}
