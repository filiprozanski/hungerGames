package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

public class StartGraphic {

	public static void main(String[] args) {
		Game g = new Game();
		
		int sizeX = g.getBoardSize_X();
		int sizeY = g.getBoardSize_Y();
		int shipsNumber = g.getShipsNumber();
		int[] shipsType = g.getShipTypes();
		
		PlayerStatus p1 = new PlayerStatus(sizeX, sizeY, shipsNumber, shipsType);
		SettingPresenter pres = new SettingPresenter(g, p1);

		ShipSettingPanel panel = (ShipSettingPanel) pres.getView();
		JFrame f = new JFrame();
		f.getContentPane().add(panel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(660, 660);
		f.setVisible(true);
	}

}
