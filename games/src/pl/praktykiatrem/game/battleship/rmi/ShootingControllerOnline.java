package pl.praktykiatrem.game.battleship.rmi;

import pl.praktykiatrem.game.battleship.gameComponents.Place;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.ShootResult;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForTwoPlayers;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;

/**
 * 
 * Klasa <code>ShootingController</code>
 *
 * Koordynuje faz� strzelania, po�rednicz�c pomi�dzy prezenterami, kt�re
 * obs�uguj� interfejs graficzny
 *
 * @author filipr
 * @version 30 lip 2014 15:10:51
 *
 */
public class ShootingControllerOnline implements IShootingController {
	/**
	 * obiekt reprezentuj�cy pierwszego z graczy
	 */
	private PlayerStatus player1;
	/**
	 * obiekt reprezentuj�cy drugiego z graczy
	 */
	private PlayerStatus player2;
	/**
	 * obiekt reprezentuj�cy presenter gracza player1
	 */
	private IShootingPresenterControll pres1;
	/**
	 * obiekt reprezentuj�cy persenter gracza player2
	 */
	private IShootingPresenterControll pres2;
	/**
	 * obiekt, kt�ry udost�pnia akcje wykonywane w grze
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
	 * skuteczno�� gracza
	 */
	private int accuracy;

	private StartGraphicForTwoPlayers supervisor;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingController</code>
	 *
	 * @param player1
	 * @param player2
	 * @param g
	 */
	public ShootingControllerOnline(PlayerStatus player1, PlayerStatus player2,
			Game g, StartGraphicForTwoPlayers supervisor) {
		this.player1 = player1;
		this.player2 = player2;
		this.supervisor = supervisor;
		this.g = g;

		pres1 = new ShootingPresenter(g, player1, this);
		pres2 = new ShootingPresenter(g, player2, this);

		pres1.setStats(g.getShipsNumber(), g.getShipsNumber());
		pres2.setStats(g.getShipsNumber(), g.getShipsNumber());

		pres1.changeStatus(true);
		pres2.changeStatus(false);

		pres1.showFrame();
		pres2.showFrame();

	}

	/**
	 * 
	 * Metoda <code>makeMove</code>
	 * 
	 * koordynuje ruch, wykonuj�c odpowiednie akcje na presenterach
	 * poszczeg�lnych graczy
	 *
	 * @param player
	 *            gracz strzelaj�cy
	 * @param x
	 *            wsp�rz�dna x strza�u
	 * @param y
	 *            wsp�rz�dna y strza�u
	 * @return true je�li trafiony, inaczej false
	 */
	@Override
	public boolean makeMove(PlayerStatus player, int x, int y) {
		if (player.equals(player1)) {
			return makeMove(player1, player2, x, y);
		} else {
			return makeMove(player2, player1, x, y);
		}
	}

	private boolean makeMove(PlayerStatus shooter, PlayerStatus victim, int x,
			int y) {
		ShootResult result = g.makeMove(victim, x, y);
		switch (result) {
		case HIT:
			boardSettingHit(shooter, victim, x, y);
			return true;
		case SINK:
			int id = g.getShipID(victim, x, y);
			boardSettingSink(shooter, victim, x, y, id);
			if (victim.getShipsNumber() == 0) {
				gameOver(shooter);
			}
			return true;
		case MISS:
			boardSettingMiss(shooter, victim, x, y);
			return false;
		default:
			return false;
		}
	}

	private void drawLeftShips(PlayerStatus opponent) {

		IShootingPresenterControll playerPres = getPresenter(getOpposePlayer(opponent));

		for (int j = 0; j < g.getBoardSizeH(); j++)
			for (int i = 0; i < g.getBoardSizeV(); i++) {
				Place place = opponent.getPlace(i, j);
				if (place.isShipOnPlace()
						&& opponent.getPlace(i, j).isPlaceInGame())
					playerPres.fchangeIcon(i, j, place.getShipId() + 1);

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
		IShootingPresenterControll spres = getPresenter(shooter);
		spres.changeShipState(id);
		spres.drawShip(g.getCoordsTable(victim, id));
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

	}

	/**
	 * 
	 * Metoda <code>getPresenter</code>ss
	 * 
	 * wi��e obiekt gracza z odpowiednim presenterem
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

	private PlayerStatus getOpposePlayer(PlayerStatus player) {
		if (player == player1)
			return player2;
		else if (player == player2)
			return player1;
		else
			return null;
	}

	public void gameOver(PlayerStatus player) {
		if (player.equals(player1)) {
			drawLeftShips(player1);
			pres1.gameOver(true);
			pres2.gameOver(false);

		} else if (player.equals(player2)) {
			drawLeftShips(player2);
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

		drawLeftShips(player1);
		drawLeftShips(player2);
		pres1.changeGiveUpButtonLabel();
		pres2.changeGiveUpButtonLabel();
	}

	@Override
	public void callMenu() {
		pres1.closeFrame();
		pres2.closeFrame();

		supervisor.callMenu();
	}

	@Override
	public void setHint() {
		// brak tej opcji w tym trybie

	}
}
