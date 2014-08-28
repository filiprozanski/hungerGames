package pl.praktykiatrem.game.battleship.graphic.tabularasa;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.interfaces.IStage;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.interfaces.IStartGame;
import pl.praktykiatrem.game.battleship.rules.GameRules;

public class ShootingController implements IStage, IShootingController {
	private IStartGame gameStarter;
	private GameRules gameRules;
	private IShootingController shootingController1;
	private IShootingController shootingController2;

	public ShootingController(IStartGame gameStarter, GameRules gameRules,
			IShootingController controller1, IShootingController controller2) {
		this.gameStarter = gameStarter;
		this.gameRules = gameRules;
		this.shootingController1 = controller1;
		this.shootingController2 = controller2;
	}

	@Override
	public void startStage() {

	}

	@Override
	public boolean makeMove(PlayerStatus player, Coordinates coords)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resign(PlayerStatus player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHint() {
		// TODO Auto-generated method stub

	}

	@Override
	public Coordinates[] getCoordsTable(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
