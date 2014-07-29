package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.panels.IShootingView;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ShootingPresenter implements IShootingPresenter {
    private Game gameRules;
    private PlayerStatus player;
    private IShootingView view;

    public ShootingPresenter(Game gameRules, PlayerStatus player) {
	this.gameRules = gameRules;
	this.player = player;

	view = new ShootingPanel(this);
	view.initialize(gameRules.getBoardSize_H(), gameRules.getBoardSize_V());
	view.disableAllPlayerBoardPlaces();
    }
}
