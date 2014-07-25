package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.rules.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;

public class SettingPresenter implements ISettingPresenter {
	private int polesNumber = 3;
	private int id;

	Game gameRules;
	PlayerStatus player;
	ISettingView view;

	public SettingPresenter(Game gameRules, PlayerStatus player,
			ISettingView view) {
		this.gameRules = gameRules;
		this.player = player;
		this.view = view;
	}

	public ISettingView getView() {
		return view;
	}

	public SettingPresenter(Game gameRules) {
		System.out.println("1");
		this.gameRules = gameRules;
		System.out.println("2");
		initializePlayer();
		System.out.println("3");
		view = new ShipSettingPanel(this);
		System.out.println("!");
	}

	private void initializePlayer() {
		int sizeX = gameRules.getBoardSize_X();
		int sizeY = gameRules.getBoardSize_Y();
		int shipsNumber = gameRules.getShipsNumber();
		int[] shipsType = gameRules.getShipTypes();

		player = new PlayerStatus(sizeX, sizeY, shipsNumber, shipsType);
	}

	@Override
	public void shipChoiceDone(int polesNumber, int id) {
		this.polesNumber = polesNumber;
		this.id = id;
	}

	@Override
	public void placeShip(int x, int y, int freq) {
		switch (freq) {
		case 1:
			putInHorizontalDirection(x, y);
			break;
		case 2:
			clearLastChoice(x, y, Direction.HORIZONTAL);
			putInVerticalDirection(x, y);
			break;
		case 0:
			clearLastChoice(x, y, Direction.VERTICAL);
			break;
		}
	}

	private void putInVerticalDirection(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.VERTICAL,
				x, y))
			drawShipOnBoard(x, y, Direction.VERTICAL);

	}

	private void putInHorizontalDirection(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.HORIZONTAL,
				x, y))
			drawShipOnBoard(x, y, Direction.HORIZONTAL);

	}

	public void clearLastChoice(int x, int y, Direction dir) {
		gameRules.displaceShips(player, id, polesNumber, dir, x, y);
	}

	private void drawShipOnBoard(int x, int y, Direction dir) {
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, 2);
				y++;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				view.changePlaceIcon(x, y, 2);
				x++;
			}
		}
	}
}
