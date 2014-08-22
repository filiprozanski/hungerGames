package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class ServerApp {
	public ServerApp(RulesType rulesType) throws RemoteException,
			AlreadyBoundException {
		Registry r = LocateRegistry.createRegistry(IRMIServer.PORTNUMBER);
		r.bind(IRMIServer.SERVICE_NAME, new RMIServer());
		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("sleep");
			e.printStackTrace();
		}
	}
}
