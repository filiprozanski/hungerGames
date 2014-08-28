package pl.praktykiatrem.game.battleship.graphic.tabularasa.setting;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.setting.SwingPresenter;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class PlayerSettingController implements IPlayerSettingController,
		IPlayerController {
	private GameRules gameRules;
	private PlayerStatus player;
	private ISettingPresenterControll presenter;
	private ISettingController controller;

	public PlayerSettingController(GameRules gameRules, PlayerStatus player,
			ISettingController controller) {
		this.gameRules = gameRules;
		this.player = player;
		this.controller = controller;

		presenter = new SwingPresenter(gameRules.getConstants(), this, player);
	}

	@Override
	public boolean placeShips(int id, int polesNumber, Direction dir,
			Coordinates coords) {
		return gameRules.placeShips(player, id, polesNumber, dir, coords);
	}

	@Override
	public void placeShipAtRandom() {
		Direction rand_dir;
		int randX;
		int randY;
		int polesNumber;
		presenter.resetBoard();
		resetGame();

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
		controller.playerIsReady();
	}

	@Override
	public void playerIsNotReady() {
		controller.playerIsNotReady();
	}

	@Override
	public int getActiveShipsNumber() {
		return gameRules.getActiveShipsNumber(player);
	}

	@Override
	public boolean displaceShip(int id, int polesNumber, Direction dir,
			Coordinates coords) {
		return gameRules.displaceShips(player, id, polesNumber, dir, coords);
	}

	@Override
	public void resetGame() {
		gameRules.resetGame(player);
	}

	@Override
	public void startStage() {
		presenter.showFrame();
	}

	@Override
	public void endStage() {
		presenter.closeFrame();
	}
}
