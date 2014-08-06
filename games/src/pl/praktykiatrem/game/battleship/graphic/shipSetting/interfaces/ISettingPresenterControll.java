package pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;

public interface ISettingPresenterControll extends Remote {
	public void resetBoard();

	public void playerIsReady() throws RemoteException;

	public void showFrame() throws RemoteException;

	public void closeFrame() throws RemoteException;

	public void placeShipsOnView(int randX, int randY, Direction rand_dir,
			int i, int polesNumber) throws RemoteException;
}
