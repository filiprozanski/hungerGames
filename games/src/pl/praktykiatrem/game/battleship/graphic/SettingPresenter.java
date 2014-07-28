package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.rules.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingPresenter implements ISettingPresenter {
	private int polesNumber;
	private int id;

	Game gameRules;
	PlayerStatus player;
	ISettingView view;

	public SettingPresenter(Game gameRules, PlayerStatus player) {
		this.gameRules = gameRules;
		this.player = player;
		view = new ShipSettingPanel(this);
		view.initialize(gameRules.getShipTypes(), gameRules.getBoardSize_H(),
				gameRules.getBoardSize_V());
		view.disableAllBoardPlaces();
	}

	public ISettingView getView() {
		return view;
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
		Coordinates tab[] = player.getCoordsTable(id);
		// view.enableAllBoardPlaces();
		if (!player.isShipSet(id)) {
			view.enableAllBoardPlaces();
			for (int a = 0; a < player.getShipsNumber(); a++) {
				tab = player.getCoordsTable(a);
				for (int i = 0; i < tab.length; i++) {
					view.disableOneBoardPlace(tab[i].getX(), tab[i].getY());
				}
			}
		} else {
			view.disableAllBoardPlaces();
			view.enableOneBoardPlace(tab[0].getX(), tab[0].getY());
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
			view.changeButtonCallNumber(x, y);
			break;
		case 0:
			clearLastChoice(x, y, Direction.VERTICAL);
			view.changeButtonCallNumber(x, y);
			break;
		}
	}

	private boolean secondClick(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.VERTICAL,
				x, y)) {
			drawShipOnBoard(x, y, Direction.VERTICAL);

			return true;
		}
		return false;
	}

	private void firstClick(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.HORIZONTAL,
				x, y)) {
			drawShipOnBoard(x, y, Direction.HORIZONTAL);
			view.changeButtonCallNumber(x, y);
		} else {
			if (secondClick(x, y)) {
				view.changeButtonCallNumber(x, y);
				view.changeButtonCallNumber(x, y);
			}
		}
	}

	public void clearLastChoice(int x, int y, Direction dir) {
		if (gameRules.displaceShips(player, id, polesNumber, dir, x, y)) {
			drawBlankOnBoard(x, y, dir);
			// view.enableAllBoardPlaces();
		}
	}

	private void drawShipOnBoard(int x, int y, Direction dir) {
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, 3);
				y++;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, 3);
				x++;
			}
		}
	}

	private void drawBlankOnBoard(int x, int y, Direction dir) {
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, 0);
				y++;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, 0);
				x++;
			}
		}
	}
}
