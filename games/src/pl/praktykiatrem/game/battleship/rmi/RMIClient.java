package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnline;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.PlayerStatus;

public class RMIClient extends UnicastRemoteObject implements IRMIClient {
	private Registry r;

	private IRMIServer server;

	private StartGraphicOnline starter;

	public RMIClient(StartGraphicOnline starter) throws RemoteException,
			NotBoundException {
		super();

		this.starter = starter;

		r = LocateRegistry.getRegistry("localhost", IRMIServer.PORTNUMBER);
		server = (IRMIServer) r.lookup(IRMIServer.SERVICE_NAME);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1921402231416408470L;

	@Override
	public void changeStage() throws RemoteException {
		starter.changeStage();
	}

	public Game getGame() throws RemoteException {
		return server.getGame();
	}

	public boolean makeMove(PlayerStatus player, int x, int y) {
		return server.makeMove(player, x, y);
	}

	public void setPlayer(BSPlayerStatus player) throws RemoteException {
		server.setPlayer(player, this);

	}

	@Override
	public void hitSetting(int x, int y, int playerShipsNumber,
			int enemyShipsNumber, int accuracy) throws RemoteException {
		starter.hitSetting(x, y, playerShipsNumber, enemyShipsNumber, accuracy);
	}

	@Override
	public void missSetting(int x, int y) throws RemoteException {
		starter.missSetting(x, y);
	}

	@Override
	public void loseShipSetting(int x, int y) throws RemoteException {
		starter.loseShipSetting(x, y);
	}

	@Override
	public void shipSunkSetting(int x, int y) throws RemoteException {
		starter.shipSunkSetting(x, y);
	}

}
