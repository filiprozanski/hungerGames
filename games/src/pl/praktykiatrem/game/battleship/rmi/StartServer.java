package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class StartServer {

	public static void main(String[] args) throws RemoteException,
			AlreadyBoundException {
		new ServerApp(RulesType.CUSTOMRULES);
	}

}
