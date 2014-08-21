package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingControllerOnline;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class ServerApp {
	private PlayerStatus player1;

	private PlayerStatus player2;

	private Game game;

	private ShootingControllerOnline shController;

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

	public void stageB() {
		shController = new ShootingControllerOnline(player1, player2, game,
				this);
	}

	public void changeStage() {
		System.out.println("Etap strzelania");
		stageB();
	}
}
