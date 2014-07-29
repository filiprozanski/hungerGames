package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

public class StartGraphic {

    private static JFrame frame1;
    private static JFrame frame2;
    private static PlayerStatus player1;
    private static PlayerStatus player2;
    private static Game game;

    public static void main(String[] args) {
	StartGraphic start = new StartGraphic();

	start.initialize();
	start.stageB();
    }

    public void initialize() {
	game = new Game();

	int sizeX = game.getBoardSize_H();
	int sizeY = game.getBoardSize_V();
	int[] shipsType = game.getShipTypes();

	player1 = new PlayerStatus(sizeX, sizeY, shipsType);
	player2 = new PlayerStatus(sizeX, sizeY, shipsType);

	player1.setName("Filip");
	player2.setName("Wiktor");

	frame1 = new JFrame(player1.getName());
	frame2 = new JFrame(player2.getName());

	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame1.setSize(660, 660);
	frame1.setLocationByPlatform(true);

	frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame2.setSize(660, 660);
	frame2.setLocationByPlatform(true);
    }

    public void stageA() {
	ISettingPresenter pres1 = new SettingPresenter(game, player1);
	ISettingPresenter pres2 = new SettingPresenter(game, player2);

	frame1.getContentPane().add((ShipSettingPanel) pres1.getView());
	frame1.setVisible(true);

	frame2.getContentPane().add((ShipSettingPanel) pres2.getView());
	frame2.setVisible(true);
    }

    public void stageB() {
	IShootingPresenter pres1 = new ShootingPresenter(game, player1);
	IShootingPresenter pres2 = new ShootingPresenter(game, player2);

	frame1.getContentPane().removeAll();
	frame2.getContentPane().removeAll();

	frame1.getContentPane().add((ShootingPanel) pres1.getView());
	frame2.getContentPane().add((ShootingPanel) pres2.getView());

	frame1.setVisible(true);
	frame2.setVisible(true);
    }
}
