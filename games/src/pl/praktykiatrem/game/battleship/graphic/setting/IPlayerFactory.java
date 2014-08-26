package pl.praktykiatrem.game.battleship.graphic.setting;

import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.IPlayerController;
import pl.praktykiatrem.game.battleship.rules.Game;

public interface IPlayerFactory {
	IPlayerController createPlayerControllerForShipPlacement(Game gameRules,
			PlayerStatus player, SettingController controller, PlayerType type);

	IPlayerController createPlayerControllerForMainGame(Game gameRules,
			PlayerStatus player, SettingController controller, PlayerType type);
}
