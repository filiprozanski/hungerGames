package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMITest {

	public static void main(String[] args) throws Exception {
		RMIServer server = new RMIServer();
		Registry r = LocateRegistry.createRegistry(9875);
		r.bind("RMIServer", server);
	}

}
