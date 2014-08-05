package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingControllerForTwoPlayers;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingControllerForTwoPlayers;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.menu.IMenuCallObserver;

public class StartGraphicForTwoPlayers {

	private JFrame frame1;

	private JFrame frame2;

	private PlayerStatus player1;

	private PlayerStatus player2;

	private Game game;

	private ShootingControllerForTwoPlayers shController;

	private SettingControllerForTwoPlayers seController;

	private IMenuCallObserver menuObserver;

	/*
	 * public static void main(String[] args) { StartGraphicLocal start = new
	 * StartGraphicLocal();
	 * 
	 * start.initialize(); start.stageA(); }
	 */

	public StartGraphicForTwoPlayers(String name1, String name2,
			IMenuCallObserver menuObserver, int rulesType) {
		this.menuObserver = menuObserver;
		game = new Game(rulesType);
		initialize(name1, name2);
		stageA();
	}

	public void initialize(String name1, String name2) {
		int sizeX = game.getBoardSizeH();
		int sizeY = game.getBoardSizeV();
		int[] shipsType = game.getShipTypes();

		player1 = new PlayerStatus(sizeX, sizeY, shipsType);
		player2 = new PlayerStatus(sizeX, sizeY, shipsType);

		player1.setName(name1);
		player2.setName(name2);

		/*
		 * frame1 = new JFrame(player1.getName()); frame2 = new
		 * JFrame(player2.getName());
		 * 
		 * frame1.setResizable(false); frame2.setResizable(false);
		 * 
		 * frame1.setBackground(new Color(135, 206, 235));
		 * frame2.setBackground(new Color(135, 206, 235));
		 * 
		 * frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame1.setLocationByPlatform(true);
		 * 
		 * frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame2.setSize(660, 660); frame2.setLocationByPlatform(true);
		 */
	}

	public void stageA() {
		seController = new SettingControllerForTwoPlayers(game, player1,
				player2, this);

		/*
		 * frame1.getContentPane().add((ShipSettingPanel)
		 * seController.getView(1)); frame1.setSize(660, 660);
		 * frame1.setVisible(true);
		 * 
		 * frame2.getContentPane().add((ShipSettingPanel)
		 * seController.getView(2)); frame2.setSize(660, 660);
		 * frame2.setVisible(true);
		 */
	}

	public void stageB() {
		shController = new ShootingControllerForTwoPlayers(player1, player2,
				game, this);

		/*
		 * frame1.getContentPane().removeAll();
		 * frame2.getContentPane().removeAll();
		 * 
		 * frame1.getContentPane().add((ShootingPanel) shController.getView(1));
		 * frame2.getContentPane().add((ShootingPanel) shController.getView(2));
		 * 
		 * frame1.setSize(660, 660); frame2.setSize(660, 660);
		 * 
		 * frame1.setVisible(true); frame2.setVisible(true);
		 */
	}

	public void changeStage() {
		stageB();
	}

	public void callMenu() {
		/*
		 * frame1.dispose(); frame2.dispose();
		 */
		menuObserver.callMenu();
	}
}