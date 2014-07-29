package pl.praktykiatrem.game.battleship.graphic;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.rules.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingPresenter implements ISettingPresenter {
	private int polesNumber;
	private int id;

	private Game gameRules;
	private PlayerStatus player;
	private ISettingView view;
	private ArrayList<Coordinates> locked;

	public SettingPresenter(Game gameRules, PlayerStatus player) {
		this.gameRules = gameRules;
		this.player = player;
		locked = new ArrayList<Coordinates>();
		view = new ShipSettingPanel(this);
		view.initialize(gameRules.getShipTypes(), gameRules.getBoardSize_H(),
				gameRules.getBoardSize_V());
		view.disableAllBoardPlaces();
	}

	public ISettingView getView() {
		return view;
	}

	private void getLockedPlaces() {
		ArrayList<Coordinates> tab = new ArrayList<Coordinates>();

		for (int a = 0; a < player.getShipsNumber(); a++)
			tab.addAll(player.getCoords(a));

		this.locked = tab;
	}

	private void initializePlayer() {
		int sizeX = gameRules.getBoardSize_H();
		int sizeY = gameRules.getBoardSize_V();
		int shipsNumber = gameRules.getShipsNumber();
		int[] shipsType = gameRules.getShipTypes();

		player = new PlayerStatus(sizeX, sizeY, shipsType);
	}

	@Override
	public void shipChoiceDone(int polesNumber, int id) {
		this.polesNumber = polesNumber;
		this.id = id;
		Coordinates tab[] = player.getCoords(id).toArray(
				new Coordinates[player.getCoords(id).size()]); // UWAGA!!!!
		if (!player.isShipSet(id)) {
			view.enableAllBoardPlaces();
			getLockedPlaces();
			for (Coordinates coord : locked)
				view.disableOneBoardPlace(coord.getX(), coord.getY());
		} else {
			view.disableAllBoardPlaces(tab[0].getX(), tab[0].getY());
		}
	}

	@Override
	public void placeShip(int x, int y, int freq) {
		switch (freq) {
		case 1:
			firstClick(x, y);
			break;
		case 2:
			clearLastChoice(x, y, Direction.HORIZONTAL);
			secondClick(x, y);
			break;
		case 0:
			clearLastChoice(x, y, Direction.VERTICAL);
			view.changeButtonCallNumber(x, y, 1);
			break;
		}
	}

	private void firstClick(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.HORIZONTAL,
				x, y)) {
			drawOnBoard(x, y, Direction.HORIZONTAL, id + 1);
			view.changeButtonCallNumber(x, y, 2);
		} else {
			secondClick(x, y);
		}
	}

	private void secondClick(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.VERTICAL,
				x, y)) {
			drawOnBoard(x, y, Direction.VERTICAL, id + 1);
			view.changeButtonCallNumber(x, y, 0);
		} else {
			view.changeButtonCallNumber(x, y, 1);
		}

	}

	public void clearLastChoice(int x, int y, Direction dir) {
		if (gameRules.displaceShips(player, id, polesNumber, dir, x, y)) {
			drawOnBoard(x, y, dir, 0);
			getLockedPlaces();
			for (Coordinates coord : locked)
				view.disableOneBoardPlace(coord.getX(), coord.getY());
		}
	}

	private void drawOnBoard(int x, int y, Direction dir, int icon) {
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, icon);
				y++;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, icon);
				x++;
			}
		}
	}
}
