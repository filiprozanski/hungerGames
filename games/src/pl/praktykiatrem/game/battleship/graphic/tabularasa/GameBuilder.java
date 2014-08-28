package pl.praktykiatrem.game.battleship.graphic.tabularasa;

import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.setting.ComputerSettingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.setting.PlayerSettingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.setting.SettingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting.ComputerShootingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting.PlayerShootingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting.ShootingController;
import pl.praktykiatrem.game.battleship.rules.GameConstants;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;

public class GameBuilder {
	private SettingController settingController;
	private ShootingController shootingController;

	private PlayerSettingController playerSettingController1;
	private PlayerSettingController playerSettingController2;

	private PlayerShootingController playerShootingController1;
	private PlayerShootingController playerShootingController2;

	private ComputerSettingController computerSettingController;
	private ComputerShootingController computerShootingController;

	private PlayerStatus player1;
	private PlayerStatus player2;

	private GameRules gameRules;
	private GameType gameType;
	private Difficulty difficulty;

	private IStartGame gameStarter;

	private int playersNumber = 0;

	public GameBuilder(IStartGame starter) {
		this.gameStarter = starter;
	}

	public void setRules(GameRules rules) {
		this.gameRules = rules;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void setPlayer(String playerName) {
		if (playersNumber == 0) {
			player1 = new PlayerStatus(gameRules.getBoardSizeV(),
					gameRules.getBoardSizeH(), gameRules.getShipTypes(),
					playerName);
			playersNumber++;
		} else if (playerName != null)
			player2 = new PlayerStatus(gameRules.getBoardSizeV(),
					gameRules.getBoardSizeH(), gameRules.getShipTypes(),
					playerName);
		else
			new PlayerStatus(gameRules.getBoardSizeV(),
					gameRules.getBoardSizeH(), gameRules.getShipTypes(),
					"Computer");
	}

	public void build() {
		GameConstants gameConstants = gameRules.getConstants();

		switch (gameType) {
		case VS_COMPUTER:
			playerSettingController1 = new PlayerSettingController(gameRules,
					player1, settingController);
			computerSettingController = new ComputerSettingController(
					gameRules, player2, settingController);
			settingController = new SettingController(gameStarter,
					playerSettingController1, computerSettingController);
			playerShootingController1 = new PlayerShootingController(player1,
					shootingController, gameConstants);
			computerShootingController = new ComputerShootingController(
					player2, shootingController, difficulty, gameRules);
			shootingController = new ShootingController(gameStarter, gameRules,
					playerShootingController1, computerShootingController);
			break;
		case VS_PLAYER:
			playerSettingController1 = new PlayerSettingController(gameRules,
					player1, settingController);
			playerSettingController2 = new PlayerSettingController(gameRules,
					player2, settingController);
			settingController = new SettingController(gameStarter,
					playerSettingController1, playerSettingController2);
			playerShootingController1 = new PlayerShootingController(player1,
					shootingController, gameConstants);
			playerShootingController2 = new PlayerShootingController(player2,
					shootingController, gameConstants);
			shootingController = new ShootingController(gameStarter, gameRules,
					playerShootingController1, playerShootingController2);
			break;
		}
	}

	public PlayerSettingController getPlayerSettingController(int playerNumber) {
		switch (playerNumber) {
		case 1:
			return playerSettingController1;
		case 2:
			return playerSettingController2;
		default:
			return null;
		}
	}

	public PlayerShootingController getPlayerShootingController(int playerNumber) {
		switch (playerNumber) {
		case 1:
			return playerShootingController1;
		case 2:
			return playerShootingController2;
		default:
			return null;
		}
	}

	public SettingController getSettingController() {
		return settingController;
	}

	public ShootingController getShootingController() {
		return shootingController;
	}

}
