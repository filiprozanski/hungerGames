package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.Place;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.components.ShootResult;
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

	private RMIServer supervisor;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ShootingController</code>
	 *
	 * @param player1
	 * @param player2
	 * @param g
	 */
	public ShootingControllerOnline(PlayerStatus player1, PlayerStatus player2,
			Game g, RMIServer supervisor) {
		this.player1 = player1;
		this.player2 = player2;
		this.supervisor = supervisor;
		this.g = g;
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
	 * @throws RemoteException
	 */
	@Override
	public boolean makeMove(PlayerStatus player, Coordinates coords)
			throws RemoteException {
		if (player.equals(player1)) {
			return makeMove(player1, player2, coords);
		} else {
			return makeMove(player2, player1, coords);
		}
	}

	private boolean makeMove(PlayerStatus shooter, PlayerStatus victim,
			Coordinates coords) throws RemoteException {
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

	private void drawLeftShips(PlayerStatus opponent) {
		ArrayList<Place> leftShips = new ArrayList<Place>();

		for (int j = 0; j < g.getBoardSizeH(); j++)
			for (int i = 0; i < g.getBoardSizeV(); i++) {
				Place place = opponent.getPlace(new Coordinates(i, j));
				if (place.isShipOnPlace()
						&& opponent.getPlace(new Coordinates(i, j))
								.isPlaceInGame())
					leftShips.add(place);
			}

		supervisor.drawLeftShips(leftShips, getOpposePlayer(opponent));
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
			Coordinates coords) throws RemoteException {
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(true);
		supervisor.setHitSetting(shooter, coords, playerShips, enemyShips,
				accuracy);
		supervisor.losePoleSetting(victim, coords, enemyShips, playerShips);
	}

	private void boardSettingSink(PlayerStatus shooter, PlayerStatus victim,
			Coordinates coords, int id) throws RemoteException {
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(true);
		supervisor.losePoleSetting(victim, coords, enemyShips, playerShips);
		supervisor.shipSunkSetting(shooter, id, playerShips, enemyShips,
				accuracy);
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
			Coordinates coords) throws RemoteException {
		playerShips = g.getActiveShipsNumber(shooter);
		enemyShips = g.getActiveShipsNumber(victim);
		accuracy = shooter.getAccuracy(false);
		supervisor.missSetting(shooter, coords, playerShips, enemyShips,
				accuracy);
		supervisor.allowToMove(victim, coords);

	}

	private PlayerStatus getOpposePlayer(PlayerStatus player) {
		if (player == player1)
			return player2;
		else if (player == player2)
			return player1;
		else
			return null;
	}

	public void gameOver(PlayerStatus winner) {
		supervisor.showWinMessage(winner);
		supervisor.showLoseMessage(getOpposePlayer(winner));
		drawLeftShips(winner);
	}

	@Override
	public void resign(PlayerStatus player) {
		gameOver(getOpposePlayer(player));

		drawLeftShips(player1);
		drawLeftShips(player2);
	}

	@Override
	public void setHint() {
		// brak tej opcji w tym trybie

	}

	@Override
	public void endGame() {

	}
}
