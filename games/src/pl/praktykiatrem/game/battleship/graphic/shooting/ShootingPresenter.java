package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

public class ShootingPresenter implements IShootingPresenter {
	private Game gameRules;
	private PlayerStatus player;
	private IShootingView view;
	private ShootingController controll;
	private ArrayList<Coordinates> lockedPlaces;

	public ShootingPresenter(Game gameRules, PlayerStatus player,
			ShootingController controll) {
		this.gameRules = gameRules;
		this.player = player;
		this.controll = controll;
		this.lockedPlaces = new ArrayList<Coordinates>();

		view = new ShootingPanel(this);
		view.initialize(gameRules.getBoardSize_H(), gameRules.getBoardSize_V());
		drawShips();
		view.disableAllPlayerBoardPlaces();
	}

	private void drawShips() {

		for (int i = 0; i < gameRules.getShipsNumber(); i++)
			view.drawShipLocation(gameRules.getCoordsTable(player, i), i);

	}

	@Override
	public IShootingView getView() {
		return view;
	}

	@Override
	public void shot(int x, int y) {
		if (controll.makeMove(player, x, y)) {
			view.changeBattlePlaceIcon(x, y, 10);
			view.disableBatlleBoardPlace(x, y);
			lockedPlaces.add(new Coordinates(x, y));
		} else
			view.changeBattlePlaceIcon(x, y, 9);

	}

	@Override
	public void changeIcon(int x, int y, int type) {
		view.changePlaceIcon(x, y, type);
	}

	@Override
	public void changeStatus(boolean ableToMove) {
		view.changeStateAllEnemyBoardPlaces(ableToMove, lockedPlaces);
		view.changeStatus(ableToMove);
	}
}
