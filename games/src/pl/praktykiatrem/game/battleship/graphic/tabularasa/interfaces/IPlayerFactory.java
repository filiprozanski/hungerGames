package pl.praktykiatrem.game.battleship.graphic.tabularasa.interfaces;

import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.SettingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.enums.PlayerType;
import pl.praktykiatrem.game.battleship.rules.GameRules;

public interface IPlayerFactory {
	IPlayerSettingController createPlayerControllerForShipPlacement(GameRules gameRules,
			PlayerStatus player, SettingController controller, PlayerType type);

	IPlayerSettingController createPlayerControllerForMainGame(GameRules gameRules,
			PlayerStatus player, SettingController controller, PlayerType type);
}
