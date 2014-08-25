package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Place;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
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

		r = LocateRegistry.getRegistry(IRMIServer.SERVER_LOCAL,
				IRMIServer.PORTNUMBER);
		server = (IRMIServer) r.lookup(IRMIServer.SERVICE_NAME);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1921402231416408470L;

	@Override
	public void changeStage(boolean start) throws RemoteException {
		starter.changeStage(start);
	}

	public Game getGame() throws RemoteException {
		return server.getGame();
	}

	public boolean makeMove(PlayerStatus player, int x, int y)
			throws RemoteException {
		return server.makeMove(player, x, y);
	}

	public void setPlayer(PlayerStatus player) throws RemoteException {
		server.setPlayer(player, this);

	}

	@Override
	public void hitSetting(int x, int y, int playerShipsNumber,
			int enemyShipsNumber, int accuracy) throws RemoteException {
		starter.hitSetting(x, y, playerShipsNumber, enemyShipsNumber, accuracy);
	}

	@Override
	public void missSetting(int playerShips, int x, int y, int enemyShips,
			int accuracy) throws RemoteException {
		starter.missSetting(playerShips, x, y, enemyShips, accuracy);
	}

	@Override
	public void losePoleSetting(int x, int y, int pNumber, int eNumber)
			throws RemoteException {
		starter.losePoleSetting(x, y, pNumber, eNumber);
	}

	@Override
	public void shipSunkSetting(Coordinates[] list, int id)
			throws RemoteException {
		starter.shipSunkSetting(list, id);
	}

	@Override
	public void allowToMove(int x, int y) throws RemoteException {
		starter.allowToMove(x, y);
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
