package pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.ai.Easy;
import pl.praktykiatrem.game.battleship.ai.Hard;
import pl.praktykiatrem.game.battleship.ai.IComputer;
import pl.praktykiatrem.game.battleship.ai.Medium;
import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;

public class ComputerShootingController implements IShootingController {
	private IComputer computer;
	private PlayerStatus playerStatus;
	private IShootingController controller;

	public ComputerShootingController(PlayerStatus playerStatus,
			IShootingController controller, Difficulty difficulty,
			GameRules gameRules) {
		this.playerStatus = playerStatus;
		this.controller = controller;

		switch (difficulty) {
		case EASY:
			computer = new Easy(gameRules);
			break;
		case MEDIUM:
			computer = new Medium(gameRules);
			break;
		case HARD:
			computer = new Hard(gameRules);
			break;
		default:
			computer = new Medium(gameRules);
			break;
		}
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
