package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

public interface IShootingView {

    void changePlaceIcon(int x, int y, int type);

    void disableAllPlayerBoardPlaces();

    void disableAllEnemyBoardPlaces();

    void enableAllEnemyBoardPlaces();

    void initialize(int sizeH, int sizeV);

    void drawShipLocation(Coordinates[] tab, int id);

    void changeBattlePlaceIcon(int x, int y, int type);

}
