package pl.praktykiatrem.game.battleship.graphic.shooting;

public interface IShootingPresenter {
    public void shot(int x, int y);

    public IShootingView getView();

    void changeIcon(int x, int y, int type);

    void changeStatus(boolean ableToMove);

    void setStats(int playerShips, int enemyShips, int accuracy);

    void setStats(int playerShips, int enemyShips);
}
