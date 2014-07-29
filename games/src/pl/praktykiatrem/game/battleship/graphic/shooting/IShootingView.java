package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

public interface IShootingView {

	void changePlaceIcon(int x, int y, int type);

	void changeStatus(boolean ready);

	void disableAllPlayerBoardPlaces();

	void changeStateAllEnemyBoardPlaces(boolean enable,
			ArrayList<Coordinates> lockedPlaces);

	void initialize(int sizeH, int sizeV);

	void drawShipLocation(Coordinates[] tab, int id);

	void changeBattlePlaceIcon(int x, int y, int type);

	void disableBatlleBoardPlace(int x, int y);

}
