package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Place;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public interface IRMIClient extends Remote {
	public void changeStage(boolean start) throws RemoteException;

	public void hitSetting(int x, int y, int playerShipsNumber,
			int enemyShipsNumber, int accuracy) throws RemoteException;

	public void missSetting(int playerShips, int x, int y, int enemyShips,
			int accuracy) throws RemoteException;

	public void losePoleSetting(int x, int y, int pNumber, int eNumber)
			throws RemoteException;

	public void shipSunkSetting(Coordinates[] list, int id)
			throws RemoteException;

	public void allowToMove(int x, int y) throws RemoteException;

	void gameOver(boolean isWinner) throws RemoteException;

	void resign(PlayerStatus player) throws RemoteException;

	public void drawLeftShips(ArrayList<Place> leftShips)
			throws RemoteException;
}
