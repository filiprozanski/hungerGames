package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.Place;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnline;
import pl.praktykiatrem.game.battleship.rules.Game;

public class RMIClient extends UnicastRemoteObject implements IRMIClient {
	private Registry r;

	private IRMIServer server;

	private StartGraphicOnline starter;

	public RMIClient(StartGraphicOnline starter) throws RemoteException,
			NotBoundException {
		super();

		this.starter = starter;

		r = LocateRegistry.getRegistry(IRMIServer.SERVER_IP,
				IRMIServer.PORTNUMBER);
		server = (IRMIServer) r.lookup(IRMIServer.SERVICE_NAME);
	}

	private static final long serialVersionUID = -1921402231416408470L;

	@Override
	public void changeStage(boolean start) throws RemoteException {
		starter.changeStage(start);
	}

	public Game getGame() throws RemoteException {
		return server.getGame();
	}

	public boolean makeMove(PlayerStatus player, Coordinates coords)
			throws RemoteException {
		return server.makeMove(player, coords);
	}

	public void setPlayer(PlayerStatus player) throws RemoteException {
		server.setPlayer(player, this);

	}

	@Override
	public void hitSetting(Coordinates coords, int playerShipsNumber,
			int enemyShipsNumber, int accuracy) throws RemoteException {
		starter.hitSetting(coords, playerShipsNumber, enemyShipsNumber,
				accuracy);
	}

	@Override
	public void missSetting(int playerShips, Coordinates coords,
			int enemyShips, int accuracy) throws RemoteException {
		starter.missSetting(playerShips, coords, enemyShips, accuracy);
	}

	@Override
	public void losePoleSetting(Coordinates coords, int pNumber, int eNumber)
			throws RemoteException {
		starter.losePoleSetting(coords, pNumber, eNumber);
	}

	@Override
	public void shipSunkSetting(Coordinates[] list, int id, int eNumber,
			int pNumber, int accuracy) throws RemoteException {
		starter.shipSunkSetting(list, id, eNumber, pNumber, accuracy);
	}

	@Override
	public void allowToMove(Coordinates coords) throws RemoteException {
		starter.allowToMove(coords);
	}

	@Override
	public void gameOver(boolean isWinner) throws RemoteException {
		starter.gameOver(isWinner);
	}

	@Override
	public void resign(PlayerStatus player) throws RemoteException {
		server.resign(player);
	}

	@Override
	public void drawLeftShips(ArrayList<Place> leftShips) {
		starter.drawLeftShips(leftShips);
	}

}
