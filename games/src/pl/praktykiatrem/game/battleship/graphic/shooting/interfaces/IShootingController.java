package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public interface IShootingController {

	boolean makeMove(PlayerStatus player, int x, int y);

	void resign(PlayerStatus player);

	void callMenu();

	void setHint();

}
