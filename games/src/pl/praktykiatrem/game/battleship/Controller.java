/*
 * Plik stworzony dnia 14 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

public class Controller {
	PlayerStatus A = new PlayerStatus();
	PlayerStatus B = new PlayerStatus();
	Game gameRules = new Game();

	public void setName(String name, int id) {
		switch (id) {
		case 1:
			A.setName(name);
			break;
		case 2:
			B.setName(name);
			break;
		}
	}

	public void showName() {
		System.out.println(A.getName());
	}

	public boolean killEmAll(PlayerStatus player, int x, int y) {
		return gameRules.makeMove(player, x, y);
	}
}
