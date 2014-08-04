package pl.praktykiatrem.game.battleship.console.vsgraphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingView;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingPresenter;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingControllerForConsole implements ISettingController {
	private int readyPlayers;
	private StartGraphicForConsole supervisor;
	private ISettingPresenter pres1;
	private ConsoleSettingPresenter pres2;

	public SettingControllerForConsole(Game g, PlayerStatus player1,
			PlayerStatus player2, StartGraphicForConsole supervisor) {

		this.supervisor = supervisor;
		pres1 = new SettingPresenter(g, player1, this);
		pres2 = new ConsoleSettingPresenter(g, player2, this);
		readyPlayers = 0;
	}

	@Override
	public ISettingView getView(int p) {
		return pres1.getView();
	}

	public void playerIsReady() {
		readyPlayers++;
		if (readyPlayers == 2)
			supervisor.changeStage();
	}

	public void playerIsNotReady() {
		readyPlayers = 0;
	}
}
