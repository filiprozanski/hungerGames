package pl.praktykiatrem.game.battleship.graphic;

public class SettingController implements IStageObserver {
	private int readyPlayers;
	private StartGraphic supervisor;

	public SettingController(StartGraphic supervisor) {
		this.supervisor = supervisor;
		readyPlayers = 0;
	}

	public void playerIsReady() {
		readyPlayers++;
		if (readyPlayers == 1) // 2 do testów //WIktor
			supervisor.changeStage();
	}
}
