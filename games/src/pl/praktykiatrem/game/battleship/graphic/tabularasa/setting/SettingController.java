package pl.praktykiatrem.game.battleship.graphic.tabularasa.setting;

import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.IStage;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.IStartGame;

public class SettingController implements ISettingController, IStage {
	private IStartGame supervisor;
	private IPlayerController playerSetting1;
	private IPlayerController playerSetting2;

	private int readyPlayers = 0;

	public SettingController(IStartGame supervisor,
			IPlayerController playerController1,
			IPlayerController playerController2) {
		this.supervisor = supervisor;
		this.playerSetting1 = playerController1;
		this.playerSetting2 = playerController2;
	}

	@Override
	public void playerIsReady() {
		readyPlayers++;
		if (readyPlayers == 2) {
			this.endStage();
			supervisor.changeStage();
		}
	}

	@Override
	public void playerIsNotReady() {
		readyPlayers = 0;
	}

	@Override
	public void startStage() {
		playerSetting1.startStage();
		playerSetting2.startStage();
	}

	public void endStage() {
		playerSetting1.endStage();
		playerSetting2.endStage();
	}
}
