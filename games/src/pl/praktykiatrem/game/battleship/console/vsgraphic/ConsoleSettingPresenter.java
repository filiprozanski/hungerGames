package pl.praktykiatrem.game.battleship.console.vsgraphic;

import java.io.FileNotFoundException;

import pl.praktykiatrem.game.battleship.console.ConsoleInteractions;
import pl.praktykiatrem.game.battleship.factory.ShipLoader;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ConsoleSettingPresenter {
	private Game gameRules;
	private PlayerStatus player;
	private ConsoleInteractions console;
	private ISettingController controller;
	private ShipLoader loader;

	public ConsoleSettingPresenter(Game gameRules, PlayerStatus player,
			ISettingController controller) {
		this.gameRules = gameRules;
		this.player = player;
		this.controller = controller;

		console = new ConsoleInteractions();
		loader = new ShipLoader(this.gameRules, console);

		setShips(player);
	}

	private void setShips(PlayerStatus gamer) {
		try {
			loader.initializeShipsFromFile(gamer);
		} catch (FileNotFoundException e) {
			loader.initializeShips(gamer, gameRules);
		}

		controller.playerIsReady();
	}
}
