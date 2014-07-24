package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.rules.Game;

public class StartGraphic {

	public static void main(String[] args) {
		Game g = new Game();
		SettingPresenter pres = new SettingPresenter(g);

	}

}
