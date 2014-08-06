package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

public interface IShootingPresenterControll extends Remote {
	public void changeStatus(boolean ableToMove) throws RemoteException;

	public void setStats(int playerShips, int enemyShips, int accuracy)
			throws RemoteException;

	public void setStats(int playerShips, int enemyShips)
			throws RemoteException;

	public void changeBattlePlaceIcon(int x, int y, int type)
			throws RemoteException;

	public void changeStateIcon(int x, int y, int type) throws RemoteException;

	public void gameOver(boolean win) throws RemoteException;

	public void fchangeIcon(int x, int y, int type) throws RemoteException;

	public void changeGiveUpButtonLabel() throws RemoteException;

	public void showFrame() throws RemoteException;

	public void closeFrame() throws RemoteException;

	public void drawShip(Coordinates[] tab) throws RemoteException;
}
