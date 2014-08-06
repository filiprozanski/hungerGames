package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForOnePlayer;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingView;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingControllerForOnePlayer implements ISettingController {
	private int readyPlayers;
	private StartGraphicForOnePlayer supervisor;
	private ISettingPresenter pres1;
	private ISettingPresenter pres2;

	public SettingControllerForOnePlayer(Game g, PlayerStatus player1,
			PlayerStatus player2, StartGraphicForOnePlayer supervisor) {
		this.supervisor = supervisor;
		pres1 = new SettingPresenter(g, player1, this);
		pres2 = new SettingPresenter(g, player2, this);
		pres2.placeShipAtRandom();
		readyPlayers = 0;
	}

	public ISettingView getView(int p) {
		return pres1.getView();
	}

	public void playerIsReady() {
		supervisor.changeStage();
	}

	public void playerIsNotReady() {
		readyPlayers = 0;
	}

}
