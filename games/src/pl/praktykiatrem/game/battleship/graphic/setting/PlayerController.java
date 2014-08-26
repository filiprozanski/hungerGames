package pl.praktykiatrem.game.battleship.graphic.setting;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.IController;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.IPlayerController;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class PlayerController implements IPlayerController {
	private Game gameRules;
	private PlayerStatus player;
	private ISettingPresenterControll presenter;
	private IController controller;

	public PlayerController(Game gameRules, PlayerStatus player,
			SettingController controller) {
		this.gameRules = gameRules;
		this.player = player;
		this.controller = controller;

		presenter = new SwingPresenter(gameRules.getConstants(), player, this);
	}

	public boolean placeShips(int id, int polesNumber, Direction dir,
			Coordinates coords) {
		return gameRules.placeShips(player, id, polesNumber, dir, coords);
	}

	public void placeShipAtRandom(ISettingPresenterControll presenter) {
		Direction rand_dir;
		int randX;
		int randY;
		int polesNumber;
		presenter.resetBoard();

		for (int i = 0; i < gameRules.getShipsNumber(); i++) {
			polesNumber = gameRules.getShipTypes()[i];
			rand_dir = Rand.getRandDirection();
			while (true) {
				randX = Rand.getRandX(gameRules);
				randY = Rand.getRandY(gameRules);
				if (placeShips(i, polesNumber, rand_dir, new Coordinates(randX,
						randY))) {

					presenter.placeShipsOnView(randX, randY, rand_dir, i,
							polesNumber);

					break;
				}
			}
		}
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
