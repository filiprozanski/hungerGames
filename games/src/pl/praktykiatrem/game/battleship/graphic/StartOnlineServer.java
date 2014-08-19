package pl.praktykiatrem.game.battleship.graphic;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingControllerOnline;
import pl.praktykiatrem.game.battleship.rmi.IRMIServer;
import pl.praktykiatrem.game.battleship.rmi.RMIServer;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class StartOnlineServer {
	private BSPlayerStatus player1;

	private BSPlayerStatus player2;

	private Game game;

	private ShootingControllerOnline shController;

	public StartOnlineServer(RulesType rulesType) throws RemoteException,
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
