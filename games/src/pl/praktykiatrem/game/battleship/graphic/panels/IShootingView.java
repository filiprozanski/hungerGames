package pl.praktykiatrem.game.battleship.graphic.panels;

public interface IShootingView {

    void changePlaceIcon(int x, int y, int type);

    void disableAllPlayerBoardPlaces();

    void disableAllEnemyBoardPlaces();

    void enableAllEnemyBoardPlaces();

    void initialize(int sizeH, int sizeV);

}
