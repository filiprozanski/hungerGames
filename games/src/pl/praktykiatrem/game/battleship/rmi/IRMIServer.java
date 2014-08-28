package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.GameRules;

public interface IRMIServer extends Remote {
	public static final String SERVICE_NAME = "hungerService";
	public static final int PORTNUMBER = 8888;
	public static final String SERVER_IP = "";
	public static final String SERVER_LOCAL = "localhost";

	public GameRules getGame() throws RemoteException;

	public void setPlayer(PlayerStatus player, IRMIClient client)
			throws RemoteException;

	public boolean makeMove(PlayerStatus player, Coordinates cords)
			throws RemoteException;

	public void resign(PlayerStatus player) throws RemoteException;
}
