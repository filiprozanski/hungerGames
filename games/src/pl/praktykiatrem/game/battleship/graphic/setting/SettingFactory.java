package pl.praktykiatrem.game.battleship.graphic.setting;

import pl.praktykiatrem.game.battleship.graphic.GameType;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.IPlayer;

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
