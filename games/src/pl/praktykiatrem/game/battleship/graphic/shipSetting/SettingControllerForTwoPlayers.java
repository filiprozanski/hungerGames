package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForTwoPlayers;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingControllerForTwoPlayers implements ISettingController {
	private int readyPlayers;
	private StartGraphicForTwoPlayers supervisor;
	private ISettingPresenter pres1;
	private ISettingPresenter pres2;

	public SettingControllerForTwoPlayers(Game g, PlayerStatus player1,
			PlayerStatus player2, StartGraphicForTwoPlayers supervisor) {
		this.supervisor = supervisor;

		pres1 = new SettingPresenter(g, player1, this);
		pres2 = new SettingPresenter(g, player2, this);

		pres1.showFrame();
		pres2.showFrame();

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
		if (readyPlayers == 2) {
			pres1.closeFrame();
			pres2.closeFrame();
			supervisor.changeStage();
		}
	}

	public void playerIsNotReady() {
		readyPlayers = 0;
	}

}