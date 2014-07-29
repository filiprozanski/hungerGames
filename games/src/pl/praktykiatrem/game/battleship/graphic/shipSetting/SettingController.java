package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import pl.praktykiatrem.game.battleship.graphic.StartGraphic;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;

public class SettingController implements IStageObserver {
    private int readyPlayers;
    private StartGraphic supervisor;

    public SettingController(StartGraphic supervisor) {
	this.supervisor = supervisor;
	readyPlayers = 0;
    }

    public void playerIsReady() {
	readyPlayers++;
	if (readyPlayers == 2)
	    supervisor.changeStage();
    }
}
