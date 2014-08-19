package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {
	private static final long serialVersionUID = -8259936918557298858L;
	private BSPlayerStatus player1;
	private BSPlayerStatus player2;
	private Game game;
	private IRMIClient client1;
	private IRMIClient client2;

	public RMIServer() throws RemoteException {
		super();
		System.out.println("Server start");
		game = new Game(RulesType.CUSTOMRULES);
	}

	@Override
	public Game getGame() throws RemoteException {
		return game;
	}

	@Override
	public void setPlayer(BSPlayerStatus player, IRMIClient client)
			throws RemoteException {
		if (player1 == null) {
			player1 = player;
			client1 = client;
		} else {
			player2 = player;
			client2 = client;

			client1.changeStage();
			client2.changeStage();
		}
	}
}
