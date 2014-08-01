package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicLocal;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingController {
	private int readyPlayers;
	private StartGraphicLocal supervisor;
	private ISettingPresenter pres1;
	private ISettingPresenter pres2;

	public SettingController(Game g, PlayerStatus player1,
			PlayerStatus player2, StartGraphicLocal supervisor) {
		this.supervisor = supervisor;
		pres1 = new SettingPresenter(g, player1, this);
		pres2 = new SettingPresenter(g, player2, this);
		readyPlayers = 0;
	}

	public ISettingView getView(int p) {
		switch (p) {
		case 1:
			return pres1.getView();
		case 2:
			return pres2.getView();
		default:
			return null;
		}
	}

	public void playerIsReady() {
		readyPlayers++;
		if (readyPlayers == 2)
			supervisor.changeStage();
	}

}
