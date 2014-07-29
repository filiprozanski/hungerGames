package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ShootingController {
    private PlayerStatus player1;
    private PlayerStatus player2;
    private IShootingPresenter pres1;
    private IShootingPresenter pres2;
    private Game g;

    ShootingController(PlayerStatus player1, PlayerStatus player2, Game g) {
	this.player1 = player1;
	this.player2 = player2;
	this.g = g;

	pres1 = new ShootingPresenter(g, player1, this);
	pres2 = new ShootingPresenter(g, player2, this);
    }

    public IShootingView getView(int p) {
	switch (p) {
	case 1:
	    return pres1.getView();
	case 2:
	    return pres2.getView();
	default:
	    return null;
	}
    }

    public boolean makeMove(PlayerStatus player, int x, int y) {
	if (player.equals(player1))
	    return g.makeMove(player2, x, y);
	else
	    return g.makeMove(player1, x, y);
    }
}
