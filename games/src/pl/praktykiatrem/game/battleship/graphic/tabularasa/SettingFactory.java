package pl.praktykiatrem.game.battleship.graphic.tabularasa;

import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.IPlayer;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.enums.GameType;

public class SettingFactory {
	private IPlayer[] players;

	public SettingFactory(GameType type) {
		players = new IPlayer[2];
		
		switch (type) {
		case VS_PLAYER:
			players[0] = new PlayerController()
		}
	}
}
