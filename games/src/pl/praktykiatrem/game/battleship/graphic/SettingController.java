package pl.praktykiatrem.game.battleship.graphic;


public class SettingController implements IStageObserver {
    private int readyPlayers;
    private StartGraphic supervisor;

    public SettingController(StartGraphic supervisor) {
	readyPlayers = 0;
    }

    public void playerIsReady() {
	readyPlayers++;
	if (readyPlayers == 2)
	    supervisor.changeStage();
    }
}
