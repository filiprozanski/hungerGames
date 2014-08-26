package pl.praktykiatrem.game.battleship.graphic.setting;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.IStartGame;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.IController;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.IPlayerController;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class SettingController implements IController {
	private IStartGame supervisor;
	private IPlayerController player1;
	private IPlayerController player2;

	public SettingController(IStartGame supervisor, PlayerStatus player1,
			PlayerStatus player2) {
		this.supervisor = supervisor;

	}

	@Override
	public void playerIsReady() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerIsNotReady() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getActiveShipsNumber(PlayerStatus player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean displaceShip(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetGame(PlayerStatus player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeShipAtRandom(ISettingPresenterControll presenter,
			PlayerStatus player) {
		// TODO Auto-generated method stub

	}

}
