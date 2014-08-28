package pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.components.ShootResult;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.GameConstants;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.uniElements.Player;

public class PlayerShootingController implements IShootingPlayerController,
		IPlayerController {
	private PlayerStatus playerStatus;
	private IShootingController controller;
	private IShootingPresenterControll presenter;
	private GameRules gameRules;

	public PlayerShootingController(PlayerStatus playerStatus,
			IShootingController controller, GameConstants gameConstants) {
		this.playerStatus = playerStatus;
		this.controller = controller;

		presenter = new ShootingPresenter(gameConstants, this);
	}

	@Override
	public void startGame(boolean b, int shipsNumber) {
		presenter.setStats(shipsNumber, shipsNumber);
		presenter.showFrame(playerStatus.getName());
	}

	@Override
	public void makeMove(Coordinates coords) {
		controller.makeMove(coords, playerStatus.getPlayer());
	}

	@Override
	public void resign() {
		controller.resign(playerStatus.getPlayer());
	}

	@Override
	public void endGame() {
		controller.endGame();
	}

	@Override
	public void setHint() {

	}

	@Override
	public Coordinates[] getCoordsTable(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayer() {
		return playerStatus.getPlayer();
	}

	@Override
	public void gameOver(boolean isWinner) {
		presenter.changeGiveUpButtonLabel();
	}

	@Override
	public void drawLeftShips(ArrayList<Coordinates> coords) {
		presenter.drawLeftShips(coords);
	}

	@Override
	public ArrayList<Coordinates> getLeftShips() {
		return playerStatus.getLeftShips();
	}

	@Override
	public void setGameRules(GameRules gameRules) {
		this.gameRules = gameRules;
	}

	@Override
	public ShootResult makeShot(Coordinates coords) {
		ShootResult result = gameRules.makeMove(playerStatus, coords);

		switch (result) {
		case HIT:
			presenter.changeStateIcon(coords, 0);
			presenter.setStats(playerStatus.getShipsNumber(), 8); // UWAGA
			return ShootResult.HIT;
		case SINK:
			presenter.changeStateIcon(coords, 0);
			presenter.setStats(playerStatus.getShipsNumber(), 8); // UWAGA
			return ShootResult.SINK;
		case MISS:
			presenter.changeStateIcon(coords, 1);
			presenter.changeStatus(true);
			return ShootResult.MISS;
		default:
			return ShootResult.MISS;
		}

	}

	@Override
	public void setHit(Coordinates coords) {
		presenter.changeBattlePlaceIcon(coords, 2);
	}

	@Override
	public void setSink(Coordinates[] coords) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMiss(Coordinates coords) {
		presenter.changeStatus(false);
		presenter.changeBattlePlaceIcon(coords, 1);
	}
}
