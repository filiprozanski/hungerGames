package pl.praktykiatrem.game.battleship.graphic;

public interface ISettingPresenter {
    public void shipChoiceDone(int polesNumber, int id);

    public void placeShip(int x, int y, int freq);

    public ISettingView getView();
}
