package pl.praktykiatrem.game.battleship.graphic.panels;

public interface IShootingPresenter {
    public void shot(int x, int y);

    public IShootingView getView();
}
