package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.Place;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class RMIServer extends UnicastRemoteObject implements IRMIServer {
	private static final long serialVersionUID = -8259936918557298858L;
	private ShootingControllerOnline shController;
	private PlayerStatus player1;
	private PlayerStatus player2;
	private GameRules game;
	private IRMIClient client1;
	private IRMIClient client2;

	public RMIServer() throws RemoteException {
		super();
		System.out.println("Server start");
		game = new GameRules(RulesType.CUSTOMRULES);
	}

	@Override
	public GameRules getGame() throws RemoteException {
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
	public boolean makeMove(PlayerStatus player, Coordinates coords)
			throws RemoteException {
		return shController.makeMove(player, coords);
	}

	public void callMenu() {
		// TODO Auto-generated method stub

	}

	public void setHitSetting(PlayerStatus player, Coordinates coords,
			int pNumber, int eNumber, int accuracy) throws RemoteException {
		IRMIClient client = getClient(player);
		client.hitSetting(coords, pNumber, eNumber, accuracy);
	}

	public void losePoleSetting(PlayerStatus player, Coordinates coords,
			int pNumber, int eNumber) throws RemoteException {
		IRMIClient client = getClient(player);
		client.losePoleSetting(coords, pNumber, eNumber);
	}

	public void shipSunkSetting(PlayerStatus player, int id, int pNumber,
			int eNumber, int accuracy) throws RemoteException {
		IRMIClient client = getClient(player);
		client.shipSunkSetting(game.getCoordsTable(getOpponent(player), id),
				id, pNumber, eNumber, accuracy);
	}

	public void missSetting(PlayerStatus player, Coordinates coords,
			int playerShips, int enemyShips, int accuracy)
			throws RemoteException {
		IRMIClient client = getClient(player);
		client.missSetting(playerShips, coords, enemyShips, accuracy);
	}

	public void allowToMove(PlayerStatus player, Coordinates coords)
			throws RemoteException {
		IRMIClient client = getClient(player);
		client.allowToMove(coords);
	}

	private PlayerStatus getOpponent(PlayerStatus player) {
		if (player == player1)
			return player2;
		else if (player == player2)
			return player1;
		else
			return null;
	}

	public void showWinMessage(PlayerStatus winner) {
		IRMIClient client = getClient(winner);
		try {
			client.gameOver(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showLoseMessage(PlayerStatus opposePlayer) {
		IRMIClient client = getClient(opposePlayer);
		try {
			client.gameOver(false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void resign(PlayerStatus player) throws RemoteException {
		shController.resign(player);
	}

	public void drawLeftShips(ArrayList<Place> leftShips, PlayerStatus player) {
		IRMIClient client = getClient(player);
		try {
			client.drawLeftShips(leftShips);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
