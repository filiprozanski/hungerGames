package pl.praktykiatrem.game.battleship.graphic.tabularasa;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.GameConstants;

public class PlayerShootingController implements IShootingController {
	private PlayerStatus playerStatus;
	private IShootingController controller;
	private IShootingPresenterControll presenter;

	public PlayerShootingController(PlayerStatus playerStatus,
			IShootingController controller, GameConstants gameConstants) {
		this.playerStatus = playerStatus;
		this.controller = controller;

		presenter = new ShootingPresenter(gameConstants, this);
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
