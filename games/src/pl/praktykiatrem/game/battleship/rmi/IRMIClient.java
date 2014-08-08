package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.rules.GameConstants;

public interface IRMIClient extends Remote {
	public void stageA(GameConstants gameConst, BSPlayerStatus player,
			ISettingController controller) throws RemoteException,
			AlreadyBoundException;

	public void stageB() throws RemoteException;
}
