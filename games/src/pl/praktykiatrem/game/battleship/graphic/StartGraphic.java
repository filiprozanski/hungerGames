package pl.praktykiatrem.game.battleship.graphic;

import java.awt.Color;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class StartGraphic {

	private JFrame frame1;

	private JFrame frame2;

	private PlayerStatus player1;

	private PlayerStatus player2;

	private Game game;

	private IStageObserver observer;

	private ShootingController sController;

	public static void main(String[] args) {
		StartGraphic start = new StartGraphic();

		start.initialize();
		start.stageA();
	}

	public void initialize() {
		game = new Game();
		observer = new SettingController(this);

		int sizeX = game.getBoardSizeH();
		int sizeY = game.getBoardSizeV();
		int[] shipsType = game.getShipTypes();

		player1 = new PlayerStatus(sizeX, sizeY, shipsType);
		player2 = new PlayerStatus(sizeX, sizeY, shipsType);

		player1.setName("Filip");
		player2.setName("Wiktor");

		frame1 = new JFrame(player1.getName());
		frame2 = new JFrame(player2.getName());

		frame1.setResizable(false);
		frame2.setResizable(false);

		frame1.setBackground(new Color(135, 206, 235));
		frame2.setBackground(new Color(135, 206, 235));

		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLocationByPlatform(true);

		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(660, 660);
		frame2.setLocationByPlatform(true);
	}

	public void stageA() {
		ISettingPresenter pres1 = new SettingPresenter(game, player1, observer);
		ISettingPresenter pres2 = new SettingPresenter(game, player2, observer);

		frame1.getContentPane().add((ShipSettingPanel) pres1.getView());
		frame1.setSize(660, 660);
		frame1.setVisible(true);

		frame2.getContentPane().add((ShipSettingPanel) pres2.getView());
		frame2.setSize(660, 660);
		frame2.setVisible(true);
	}

	public void stageB() {
		sController = new ShootingController(player1, player2, game);

		frame1.getContentPane().removeAll();
		frame2.getContentPane().removeAll();

		frame1.getContentPane().add((ShootingPanel) sController.getView(1));
		frame2.getContentPane().add((ShootingPanel) sController.getView(2));

		frame1.setSize(660, 660);
		frame2.setSize(660, 660);

		frame1.setVisible(true);
		frame2.setVisible(true);
	}

	public void changeStage() {
		stageB();
	}
}
