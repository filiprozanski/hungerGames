package pl.praktykiatrem.game.battleship.graphic.tabularasa;

import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;

public class StartGame implements IStartGame {
	private GameBuilder gameBuilder;
	private IStage currentStage;

	public StartGame(GameRules gameRules, GameType gameType,
			Difficulty difficulty, String playerName1, String playerName2) {
		gameBuilder = new GameBuilder(this);

		gameBuilder.setRules(gameRules);
		gameBuilder.setGameType(gameType);
		gameBuilder.setDifficulty(difficulty);
		gameBuilder.setPlayer(playerName1);
		gameBuilder.setPlayer(playerName2);

		gameBuilder.build();

		currentStage = gameBuilder.getSettingController();
		currentStage.startStage();
	}

	@Override
	public void changeStage() {

	}

	@Override
	public void callMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public GameType getGameType() {
		// TODO Auto-generated method stub
		return null;
	}

}
