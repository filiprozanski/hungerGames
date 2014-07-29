package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
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
	start.stageI();
    }

    public void initialize() {
	game = new Game();

	int sizeX = game.getBoardSize_H();
	int sizeY = game.getBoardSize_V();
	int[] shipsType = game.getShipTypes();

	player1 = new PlayerStatus(sizeX, sizeY, shipsType);
	player2 = new PlayerStatus(sizeX, sizeY, shipsType);

	frame1 = new JFrame();
	frame2 = new JFrame();
    }

    public void stageI() {
	SettingPresenter pres1 = new SettingPresenter(game, player1);
	SettingPresenter pres2 = new SettingPresenter(game, player2);

	frame1.getContentPane().add((ShipSettingPanel) pres1.getView());
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame1.setSize(660, 660);
	frame1.setVisible(true);

	frame2.getContentPane().add((ShipSettingPanel) pres2.getView());
	frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame2.setSize(660, 660);
	frame2.setVisible(true);
    }
}
