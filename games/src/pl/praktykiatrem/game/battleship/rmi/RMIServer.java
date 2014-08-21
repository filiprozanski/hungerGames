package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {
	private static final long serialVersionUID = -8259936918557298858L;
	private ShootingControllerOnline shController;
	private PlayerStatus player1;
	private PlayerStatus player2;
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
	public void setPlayer(PlayerStatus player, IRMIClient client)
			throws RemoteException {
		if (player1 == null) {
			player1 = player;
			client1 = client;
		} else {
			player2 = player;
			client2 = client;

			client1.changeStage(true);
			client2.changeStage(false);
			setNextStage();
		}
	}

	private IRMIClient getClient(PlayerStatus player) {
		if (player == player1)
			return client1;
		else if (player == player2)
			return client2;
		else
			return null;
	}

	private void setNextStage() {
		shController = new ShootingControllerOnline(player1, player2, game,
				this);
	}

	@Override
	public boolean makeMove(PlayerStatus player, int x, int y)
			throws RemoteException {
		return shController.makeMove(player, x, y);
	}

	public void callMenu() {
		// TODO Auto-generated method stub

	}

	public void setHitSetting(PlayerStatus player, int x, int y, int pNumber,
			int eNumber, int accuracy) throws RemoteException {
		IRMIClient client = getClient(player);
		client.hitSetting(x, y, pNumber, eNumber, accuracy);
	}

	public void losePoleSetting(PlayerStatus player, int x, int y, int pNumber,
			int eNumber) throws RemoteException {
		IRMIClient client = getClient(player);
		client.losePoleSetting(x, y, pNumber, eNumber);
	}

	public void shipSunkSetting(PlayerStatus player, int id)
			throws RemoteException {
		IRMIClient client = getClient(player);
		client.shipSunkSetting(game.getCoordsTable(player, id), id);
	}

	public void missSetting(PlayerStatus player, int x, int y, int playerShips,
			int enemyShips, int accuracy) throws RemoteException {
		IRMIClient client = getClient(player);
		client.missSetting(playerShips, x, y, enemyShips, accuracy);
	}

	public void allowToMove(PlayerStatus player, int x, int y)
			throws RemoteException {
		IRMIClient client = getClient(player);
		client.allowToMove(x, y);
	}
}
