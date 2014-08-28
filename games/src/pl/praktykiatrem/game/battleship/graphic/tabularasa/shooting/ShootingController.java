package pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.ShootResult;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.IStage;
import pl.praktykiatrem.game.battleship.graphic.tabularasa.IStartGame;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.uniElements.Player;

public class ShootingController implements IStage, IShootingController {
	private IStartGame gameStarter;
	private GameRules gameRules;
	private IPlayerController playerController1;
	private IPlayerController playerController2;

	private Player player1;
	private Player player2;

	public ShootingController(IStartGame gameStarter, GameRules gameRules,
			IPlayerController controller1, IPlayerController controller2) {
		this.gameStarter = gameStarter;
		this.gameRules = gameRules;
		this.playerController1 = controller1;
		this.playerController2 = controller2;

		playerController1.setGameRules(gameRules);
		playerController2.setGameRules(gameRules);

		player1 = playerController1.getPlayer();
		player2 = playerController2.getPlayer();
	}

	@Override
	public void startStage() {
		playerController1.startGame(true, gameRules.getShipsNumber());
		playerController2.startGame(false, gameRules.getShipsNumber());
	}

	@Override
	public void makeMove(Coordinates coords, Player player) {
		if (player.equals(player1)) {
			ShootResult result = playerController2.makeShot(coords);

			switch (result) {
			case HIT:
				playerController1.setHit();
				break;
			case SINK:
				playerController1.setSink();
				break;
			case MISS:
				playerController1.setMiss();
				break;

			}
		}
	}

	private boolean makeMove(Player shooter, Player victim, Coordinates coords) {
		ShootResult result = g.makeMove(victim, coords);
		switch (result) {
		case HIT:
			boardSettingHit(shooter, victim, coords);
			return true;
		case SINK:
			int id = g.getShipID(victim, coords);
			boardSettingSink(shooter, victim, coords, id);
			if (victim.getShipsNumber() == 0) {
				gameOver(shooter);
			}
			return true;
		case MISS:
			boardSettingMiss(shooter, victim, coords);
			return false;
		default:
			return false;
		}
	}

	@Override
	public void resign(Player player) {
		if (player.equals(player2)) {
			playerController1.gameOver(true);
			playerController2.gameOver(false);

		} else if (player.equals(player1)) {
			playerController1.gameOver(false);
			playerController2.gameOver(true);
		}

		playerController1.drawLeftShips(playerController2.getLeftShips());
		playerController2.drawLeftShips(playerController1.getLeftShips());
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub

	}
}
