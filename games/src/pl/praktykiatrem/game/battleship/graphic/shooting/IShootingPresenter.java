package pl.praktykiatrem.game.battleship.graphic.shooting;

public interface IShootingPresenter {
    public void shot(int x, int y);

    public IShootingView getView();
}
