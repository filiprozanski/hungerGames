package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.Easy;
import pl.praktykiatrem.game.battleship.ArtificialIntelligence.Hard;
import pl.praktykiatrem.game.battleship.ArtificialIntelligence.IComputer;
import pl.praktykiatrem.game.battleship.ArtificialIntelligence.Medium;
import pl.praktykiatrem.game.battleship.density.HintController;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Place;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.ShootResult;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForOnePlayer;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;

/**
 * 
 * Klasa <code>ShootingController</code>
 *
 * Koordynuje fazê strzelania, po¶rednicz±c pomiêdzy prezenterami, które
 * obs³uguj± interfejs graficzny
 *
 * @author filipr
 * @version 30 lip 2014 15:10:51
 *
 */
public class ShootingControllerForOnePlayer implements IShootingController {
	/**
	 * obiekt reprezentuj±cy pierwszego z graczy
	 */
	private PlayerStatus player1;
	/**
	 * obiekt reprezentuj±cy drugiego z graczy
	 */
	private PlayerStatus player2;
	/**
	 * obiekt reprezentuj±cy presenter gracza player1
	 */
	private IShootingPresenterControll pres1;
	/**
	 * obiekt reprezentuj±cy persenter gracza player2
	 */
	private IShootingPresenterControll pres2;
	/**
	 * obiekt, który udostêpnia akcje wykonywane w grze
	 */
	private Game g;
	/**
	 * liczba statków gracza
	 */
	private int playerShips;
	/**
	 * liczba statków przeciwnika
	 */
	private int enemyShips;
	/**
	 * skuteczno¶æ gracza
	 */
	private int accuracy;

	private IComputer iComputer;

	private StartGraphicForOnePlayer supervisor;

	private HintController hint;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingController</code>
	 *
	 * @param player1
	 * @param player2
	 * @param g
	 */
	public ShootingControllerForOnePlayer(PlayerStatus player1,
			PlayerStatus player2, Game g, StartGraphicForOnePlayer supervisor,
			Difficulty difficultyLevel) {
		this.player1 = player1;
		this.player2 = player2;
		hint = new HintController(g);
		this.supervisor = supervisor;
		this.g = g;

		setComputerOpponent(difficultyLevel);

		pres1 = new ShootingPresenter(g, player1, this, true);
		pres2 = new ShootingPresenter(g, player2, this);

		pres1.setStats(g.getShipsNumber(), g.getShipsNumber());
		pres1.showFrame();
	}

	private void setComputerOpponent(Difficulty difficulty) {
		switch (difficulty) {
		case EASY:
			iComputer = new Easy(g);
			break;
		case MEDIUM:
			iComputer = new Medium(g);
			break;
		case HARD:
			iComputer = new Hard(g);
			break;
		default:
			iComputer = new Medium(g);
		}
	}

	@Override
	public boolean makeMove(PlayerStatus player, int x, int y) {
		if (player.equals(player1)) {
			ShootResult result = g.makeMove(player2, x, y);
			switch (result) {
			case HIT:
				boardSettingHit(player1, player2, x, y);
				hint.setHit(x, y);
				return true;
			case SINK:
				int id = g.getShipID(player2, x, y);
				hint.setSink(id, g.getCoordsList(player2, id));
				boardSettingSink(player1, player2, x, y, id);
				if (player2.getShipsNumber() == 0) {
					gameOver(player1);
				}
				return true;
			case MISS:
				boardSettingMiss(player1, player2, x, y);
				hint.setMiss(x, y);
				makeComputedMove();
				return false;
			default:
				return false;
			}
		} else {
			ShootResult result = g.makeMove(player1, x, y);
			switch (result) {
			case HIT:
				boardSettingHit(player2, player1, x, y);
				iComputer.setHit(x, y);
				makeComputedMove();
				return true;
			case SINK:
				int id = g.getShipID(player1, x, y);
				boardSettingHit(player2, player1, x, y);
				iComputer.setSink(id, g.getCoordsList(player1, id));
				if (player1.getShipsNumber() == 0) {
					gameOver(player2);
					return true;
				}
				makeComputedMove();
				return true;
			case MISS:
				iComputer.setMiss(x, y);
				boardSettingMiss(player2, player1, x, y);
				return false;
			default:
				return false;
			}
		}
	}

	private void makeComputedMove() {
		Coordinates coords = iComputer.getCords();
		makeMove(player2, coords.getX(), coords.getY());
	}

	private void drawLeftShips() {
		for (int i = 0; i < g.getBoardSizeV(); i++)
			for (int j = 0; j < g.getBoardSizeH(); j++) {
				Place place = player2.getPlace(i, j);
				if (place.isShipOnPlace()
						&& player2.getPlace(i, j).isPlaceInGame())
					pres1.fchangeIcon(i, j, place.getShipId() + 1);

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
			int x, int y) {
		IShootingPresenterControll sPres = getPresenter(shooter);
		IShootingPresenterControll vPres = getPresenter(victim);
		vPres.changeStateIcon(x, y, 0);
		sPres.changeBattlePlaceIcon(x, y, 2);
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(true);
		sPres.setStats(playerShips, enemyShips, accuracy);
		vPres.setStats(enemyShips, playerShips);

	}

	private void boardSettingSink(PlayerStatus shooter, PlayerStatus victim,
			int x, int y, int id) {
		boardSettingHit(shooter, victim, x, y);
		pres1.changeShipState(id);
		pres1.drawShip(g.getCoordsTable(player2, id));
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
			int x, int y) {
		IShootingPresenterControll sPres = getPresenter(shooter);
		IShootingPresenterControll vPres = getPresenter(victim);

		vPres.changeStateIcon(x, y, 1);
		vPres.changeStatus(true);
		sPres.changeStatus(false);
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(false);
		sPres.setStats(playerShips, enemyShips, accuracy);
		vPres.setStats(enemyShips, playerShips);

	}

	/**
	 * 
	 * Metoda <code>getPresenter</code>ss
	 * 
	 * wi±¿e obiekt gracza z odpowiednim presenterem
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
	public void callMenu() {
		pres1.closeFrame();

		supervisor.callMenu();
	}

	@Override
	public void setHint() {
		hint.setHint(!hint.isHintOn());
	}
}
