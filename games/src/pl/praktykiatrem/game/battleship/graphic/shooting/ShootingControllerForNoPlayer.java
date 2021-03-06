package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.ai.Easy;
import pl.praktykiatrem.game.battleship.ai.Hard;
import pl.praktykiatrem.game.battleship.ai.IComputer;
import pl.praktykiatrem.game.battleship.ai.Medium;
import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.Place;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.components.ShootResult;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForNoPlayer;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;

public class ShootingControllerForNoPlayer implements IShootingController {
	/**
	 * obiekt reprezentujacy pierwszego z graczy
	 */
	private PlayerStatus player1;
	/**
	 * obiekt reprezentujacy drugiego z graczy
	 */
	private PlayerStatus player2;
	/**
	 * obiekt reprezentujacy presenter gracza player1
	 */
	private IShootingPresenterControll pres1;
	/**
	 * obiekt reprezentujacy persenter gracza player2
	 */
	private IShootingPresenterControll pres2;
	/**
	 * obiekt, kt�ry udostepnia akcje wykonywane w grze
	 */
	private Game g;
	/**
	 * liczba statk�w gracza
	 */
	private int playerShips;
	/**
	 * liczba statk�w przeciwnika
	 */
	private int enemyShips;
	/**
	 * skutecznosc gracza
	 */
	private int accuracy;
	private IComputer iComputer1;
	private IComputer iComputer2;
	private StartGraphicForNoPlayer supervisor;

	/**
	 *
	 * Tworzy nowy obiekt klasy <code>ShootingController</code>
	 *
	 * @param player1
	 * @param player2
	 * @param g
	 */
	public ShootingControllerForNoPlayer(PlayerStatus player1,
			PlayerStatus player2, Game g, StartGraphicForNoPlayer supervisor,
			Difficulty difficultyLevel) {
		this.player1 = player1;
		this.player2 = player2;
		this.supervisor = supervisor;
		this.g = g;
		setComputerOpponent(difficultyLevel);
		iComputer2 = new Hard(g);
		pres1 = new ShootingPresenter(g, player1, this, true);
		pres2 = new ShootingPresenter(g, player2, this, false);
		pres1.setStats(g.getShipsNumber(), g.getShipsNumber());
		pres1.showFrame();
		pres2.setStats(g.getShipsNumber(), g.getShipsNumber());
		pres2.showFrame();
		play();
	}

	private void setComputerOpponent(Difficulty difficulty) {
		switch (difficulty) {
		case EASY:
			iComputer1 = new Easy(g);
			break;
		case MEDIUM:
			iComputer1 = new Medium(g);
			break;
		case HARD:
			iComputer1 = new Hard(g);
			break;
		default:
			iComputer1 = new Medium(g);
		}
	}

	private void play() {
		IComputer c = iComputer1;
		while (true) {
			if (makeComputedMove(c))
				break;
			else {
				if (c == iComputer1)
					c = iComputer2;
				else if (c == iComputer2)
					c = iComputer1;
			}
		}
	}

	@Override
	public boolean makeMove(PlayerStatus player, Coordinates coords) {
		if (player.equals(player1)) {
			return makeMove(player1, player2, coords);
		} else {
			return makeMove(player2, player1, coords);
		}
	}

	private boolean makeMove(PlayerStatus shooter, PlayerStatus victim,
			Coordinates coords) {
		ShootResult result = g.makeMove(victim, coords);
		IComputer computer = getComputer(shooter);
		switch (result) {
		case HIT:
			boardSettingHit(shooter, victim, coords);
			computer.setHit(coords);
			return makeComputedMove(computer);
		case SINK:
			int id = g.getShipID(victim, coords);
			computer.setSink(id, g.getCoordsList(victim, id));
			boardSettingSink(shooter, victim, coords, id);
			if (victim.getShipsNumber() == 0) {
				gameOver(shooter);
				return true;
			}
			return makeComputedMove(computer);
		case MISS:
			computer.setMiss(coords);
			boardSettingMiss(shooter, victim, coords);
			return false;
		default:
			return false;
		}
	}

	private boolean makeComputedMove(IComputer iComputer) {
		Coordinates coords = iComputer.getCords();
		if (iComputer == iComputer1)
			return makeMove(player1, coords);
		else if (iComputer == iComputer2)
			return makeMove(player2, coords);
		else
			return false;
	}

	private void drawLeftShips() {
		for (int j = 0; j < g.getBoardSizeH(); j++)
			for (int i = 0; i < g.getBoardSizeV(); i++) {
				Place place = player2.getPlace(new Coordinates(i, j));
				if (place.isShipOnPlace()
						&& player2.getPlace(new Coordinates(i, j))
								.isPlaceInGame())
					pres1.fchangeIcon(new Coordinates(i, j),
							place.getShipId() + 1);
			}
	}

	/**
	 *
	 * Metoda <code>boardSettingHit</code>
	 *
	 * wykonuje akcje po trafieniu
	 *
	 * @param shooter
	 * @param victim
	 * @param x
	 * @param y
	 */
	private void boardSettingHit(PlayerStatus shooter, PlayerStatus victim,
			Coordinates coords) {
		IShootingPresenterControll sPres = getPresenter(shooter);
		IShootingPresenterControll vPres = getPresenter(victim);
		vPres.changeStateIcon(coords, 0);
		sPres.changeBattlePlaceIcon(coords, 2);
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(true);
		sPres.setStats(playerShips, enemyShips, accuracy); // UWAGA!!!
															// STATSY
															// KOMPUTERA DO
															// TEST�W
		vPres.setStats(enemyShips, playerShips);
	}

	private void boardSettingSink(PlayerStatus shooter, PlayerStatus victim,
			Coordinates coords, int id) {
		boardSettingHit(shooter, victim, coords);
		IShootingPresenterControll spress = getPresenter(shooter);
		spress.changeShipState(id);
		spress.drawShip(g.getCoordsTable(victim, id));
	}

	/**
	 *
	 * Metoda <code>boardSettingMiss</code>
	 *
	 * wykonuje akcje po pudle
	 *
	 * @param shooter
	 * @param victim
	 * @param x
	 * @param y
	 */
	private void boardSettingMiss(PlayerStatus shooter, PlayerStatus victim,
			Coordinates coords) {
		IShootingPresenterControll sPres = getPresenter(shooter);
		IShootingPresenterControll vPres = getPresenter(victim);
		vPres.changeStateIcon(coords, 1);
		vPres.changeStatus(true);
		sPres.changeStatus(false);
		sPres.changeBattlePlaceIcon(coords, 1);
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(false);
		sPres.setStats(playerShips, enemyShips, accuracy);
		vPres.setStats(enemyShips, playerShips);
	}

	/**
	 *
	 * Metoda <code>getPresenter</code>
	 *
	 * wiaze obiekt gracza z odpowiednim presenterem
	 *
	 * @param player
	 * @return presenter gracza
	 */
	private IShootingPresenterControll getPresenter(PlayerStatus player) {
		if (player.equals(player1))
			return pres1;
		else if (player.equals(player2))
			return pres2;
		else
			return null;
	}

	private IComputer getComputer(PlayerStatus player) {
		if (player == player1)
			return iComputer1;
		else if (player == player2)
			return iComputer2;
		else
			return null;
	}

	public void gameOver(PlayerStatus player) {
		if (player.equals(player1)) {
			pres1.gameOver(true);
			pres2.gameOver(false);
		} else if (player.equals(player2)) {
			drawLeftShips();
			pres1.gameOver(false);
			pres2.gameOver(true);
		}
		pres1.changeGiveUpButtonLabel();
		pres2.changeGiveUpButtonLabel();
	}

	@Override
	public void resign(PlayerStatus player) {
		if (player.equals(player2)) {
			pres1.gameOver(true);
			pres2.gameOver(false);
		} else if (player.equals(player1)) {
			pres1.gameOver(false);
			pres2.gameOver(true);
		}
		drawLeftShips();
		pres1.changeGiveUpButtonLabel();
		pres2.changeGiveUpButtonLabel();
	}

	@Override
	public void endGame() {
		pres1.closeFrame();
		supervisor.callMenu();
	}

	@Override
	public void setHint() {
		// TODO Auto-generated method stub
	}
}