package pl.praktykiatrem.game.battleship.graphic.tabularasa.setting;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class ComputerSettingController implements IPlayerController {
	private GameRules gameRules;
	private PlayerStatus player;
	private ISettingController controller;

	public ComputerSettingController(GameRules gameRules, PlayerStatus player,
			ISettingController controller) {
		this.gameRules = gameRules;
		this.player = player;
		this.controller = controller;
	}

	public void playerIsReady() {
		controller.playerIsReady();
	}

	public boolean placeShips(int id, int polesNumber, Direction dir,
			Coordinates coords) {
		return gameRules.placeShips(player, id, polesNumber, dir, coords);
	}

	public void placeShipAtRandom() {
		Direction rand_dir;
		int randX;
		int randY;
		int polesNumber;

		for (int i = 0; i < gameRules.getShipsNumber(); i++) {
			polesNumber = gameRules.getShipTypes()[i];
			rand_dir = Rand.getRandDirection();
			while (true) {
				randX = Rand.getRandX(gameRules);
				randY = Rand.getRandY(gameRules);
				if (placeShips(i, polesNumber, rand_dir, new Coordinates(randX,
						randY)))
					break;
			}
		}
	}

	@Override
	public void startStage() {
		placeShipAtRandom();
		playerIsReady();
	}

	@Override
	public void endStage() {

	}
}
