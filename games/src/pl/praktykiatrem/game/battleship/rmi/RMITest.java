package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMITest {

	public static void main(String[] args) throws Exception {
		Registry r = LocateRegistry.createRegistry(9875);
		RMIServer server = new RMIServer(r);
		r.bind("RMIServer", server);
	}

}
