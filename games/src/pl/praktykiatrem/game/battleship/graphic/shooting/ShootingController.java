package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ShootingController {
    private PlayerStatus       player1;

    private PlayerStatus       player2;

    private IShootingPresenter pres1;

    private IShootingPresenter pres2;

    private Game               g;

    private int                a;

    private int                b;

    private int                c;

    public ShootingController(PlayerStatus player1, PlayerStatus player2, Game g) {
        this.player1 = player1;
        this.player2 = player2;
        this.g = g;

        pres1 = new ShootingPresenter(g, player1, this);
        pres2 = new ShootingPresenter(g, player2, this);

        pres1.changeStatus(true);
        pres2.changeStatus(false);
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
        if (player.equals(player1)) {
            if (g.makeMove(player2, x, y)) {

                return true;
            } else {
                pres2.changeIcon(x, y, 9);
                pres2.changeStatus(true);
                pres1.changeStatus(false);
                a = g.getActiveShipsNumber(player1);
                b = g.getActiveShipsNumber(player2);
                c = player1.getAccuracy(false);
                pres1.setStats(a, b, c);
                return false;
            }
        } else {
            if (g.makeMove(player1, x, y)) {
                pres1.changeIcon(x, y, 8);
                a = g.getActiveShipsNumber(player2);
                b = g.getActiveShipsNumber(player1);
                c = player2.getAccuracy(true);
                pres2.setStats(a, b, c);
                return true;
            } else {
                pres1.changeIcon(x, y, 9);
                pres1.changeStatus(true);
                pres2.changeStatus(false);
                a = g.getActiveShipsNumber(player2);
                b = g.getActiveShipsNumber(player1);
                c = player2.getAccuracy(false);
                pres2.setStats(a, b, c);
                return false;
            }
        }
    }

    private void boardSettingHit(IShootingPresenter pres1, IShootingPresenter pres2, int x, int y) {
        pres2.changeIcon(x, y, 8);
        a = g.getActiveShipsNumber(player1);
        b = g.getActiveShipsNumber(player2);
        c = player1.getAccuracy(true);
        pres1.setStats(a, b, c);
    }
}
