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
	drawShips();
	view.disableAllPlayerBoardPlaces();
    }

    private void drawShips() {

	for (int i = 0; i < gameRules.getShipsNumber(); i++)
	    view.drawShipLocation(player.getCoordsTable(i), i);

    }

    public IShootingView getView() {
	return view;
    }

    public void shot(int x, int y) {

    }
}
