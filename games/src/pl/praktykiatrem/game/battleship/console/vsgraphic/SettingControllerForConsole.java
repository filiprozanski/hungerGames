package pl.praktykiatrem.game.battleship.console.vsgraphic;

import java.awt.Component;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingView;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingControllerForConsole implements ISettingController {
	private int readyPlayers;
	private StartGraphicForConsole supervisor;
	private ISettingPresenter pres1;
	private ConsoleSettingPresenter pres2;
	private JFrame f;

	public SettingControllerForConsole(Game g, PlayerStatus player1,
			PlayerStatus player2, StartGraphicForConsole supervisor) {

		this.supervisor = supervisor;
		readyPlayers = 0;
		pres1 = new SettingPresenter(g, player1, this);
		f = new JFrame("glupia konsola");
		f.getContentPane().add((Component) pres1.getView());
		f.setSize(660, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);

		pres2 = new ConsoleSettingPresenter(g, player2, this);
	}

	@Override
	public ISettingView getView(int p) {
		return pres1.getView();
	}

	@Override
	public void playerIsReady() {
		readyPlayers++;
		if (readyPlayers == 2) {
			supervisor.changeStage();
			f.dispose();
		}
	}

	@Override
	public void playerIsNotReady() {
		readyPlayers = 0;
	}
}
