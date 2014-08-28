package pl.praktykiatrem.game.battleship.graphic.tabularasa.interfaces;

import pl.praktykiatrem.game.battleship.graphic.tabularasa.enums.GameType;


public interface IStartGame {
	public void changeStage();

	public void callMenu();

	public GameType getGameType();
}
