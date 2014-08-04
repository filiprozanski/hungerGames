package pl.praktykiatrem.game.battleship.console;

import java.io.FileNotFoundException;

import pl.praktykiatrem.game.battleship.factory.ShipLoader;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ConsolePresenter {
	private Game gameRules;
	private PlayerStatus player;
	private ConsoleInteractions console;
	private SettingController controller;
	private ShipLoader loader;

	public ConsolePresenter(Game gameRules, PlayerStatus player,
			SettingController controller) {
		this.gameRules = gameRules;
		this.player = player;
		this.controller = controller;

		console = new ConsoleInteractions();
		loader = new ShipLoader(this.gameRules, console);
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
