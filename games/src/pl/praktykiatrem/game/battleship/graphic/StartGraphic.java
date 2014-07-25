package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

public class StartGraphic {

	public static void main(String[] args) {
		Game g = new Game();
		SettingPresenter pres = new SettingPresenter(g);

		ShipSettingPanel panel = (ShipSettingPanel) pres.getView();
		JFrame f = new JFrame();
		f.getContentPane().add(panel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(660, 660);
		f.setVisible(true);
	}

}
