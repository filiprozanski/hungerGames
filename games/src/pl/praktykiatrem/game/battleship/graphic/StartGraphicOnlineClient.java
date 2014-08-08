package pl.praktykiatrem.game.battleship.graphic;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.rmi.IRMIClient;
import pl.praktykiatrem.game.battleship.rmi.IRMIServer;
import pl.praktykiatrem.game.battleship.rules.GameConstants;

public class StartGraphicOnlineClient implements IRMIClient {
	private ISettingPresenterControll pres;
	private Registry r;

	public StartGraphicOnlineClient(int portNumber) throws RemoteException,
			NotBoundException, AlreadyBoundException {
		super();
		r = LocateRegistry.getRegistry("localhost", portNumber);
		IRMIServer s = (IRMIServer) r.lookup("RMIServer");
		r.bind("StartGraphicOnline", this);
		s.startGame();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9150849615735314473L;

	@Override
	public void stageA(GameConstants gameConst, BSPlayerStatus player,
			ISettingController controller) throws RemoteException,
			AlreadyBoundException {
		pres = new SettingPresenter(gameConst, player, controller);
		r.bind("presenter", pres);
	}

	@Override
	public void stageB() {
		// TODO Auto-generated method stub

	}

}
