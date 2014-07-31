package pl.praktykiatrem.game.battleship.menu;

import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class PlayCard extends JPanel {

	private IMainView mainView;
	private JPanel frame;
	private Game game;
	private PlayerStatus player;
	private IStageObserver observer;
	private ShootingController sController;

	public PlayCard(IMainView mainView, Game game, PlayerStatus player,
			IStageObserver observer, ShootingController sController) {
		this.game = game;
		this.player = player;
		this.observer = observer;
		this.sController = sController;
		initialize();
	}

	private void initialize() {
		frame = new JPanel();
		ISettingPresenter pres1 = new SettingPresenter(game, player, observer);

		frame.add((ShipSettingPanel) pres1.getView());
		frame.setSize(660, 660);
		frame.setVisible(true);
		add(frame);
	}

	public void play() {

		frame.removeAll();
		frame.add((ShootingPanel) sController.getView(1));
		frame.setSize(660, 660);
		frame.setVisible(true);

	}
}
