package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

public interface IRMIServer extends Remote {
	public static final String SERVICE_NAME = "hungerService";
	public static final int PORTNUMBER = 8888;
	public static final String SERVER_IP = "169.254.234.16";
	public static final String SERVER_LOCAL = "localhost";

	public Game getGame() throws RemoteException;

	public void setPlayer(PlayerStatus player, IRMIClient client)
			throws RemoteException;

	public boolean makeMove(PlayerStatus player, int x, int y)
			throws RemoteException;

	public void resign(PlayerStatus player) throws RemoteException;
}
