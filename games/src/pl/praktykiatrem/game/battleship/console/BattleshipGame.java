/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.console;

import java.io.FileNotFoundException;

import pl.praktykiatrem.game.battleship.factory.GameFactory;
import pl.praktykiatrem.game.battleship.factory.ShipLoader;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

/**
 * 
 * Klasa <code>BattleshipGame</code> przechowuje obiekty graczy oraz gry. Jest
 * odpowiedzialna za inicializacj� i przegieg gry.
 *
 * @author hungerGames
 *
 */
public class BattleshipGame {
	private BSPlayerStatus A;
	private BSPlayerStatus B;
	private ConsoleInteractions gameControl;
	private Game gameRules;
	private ShipLoader load;

	public BattleshipGame() {
		gameRules = new Game(RulesType.CUSTOMRULES);

		GameFactory start = new GameFactory(gameRules.getBoardSizeH(),
				gameRules.getBoardSizeV(), gameRules.getShipTypes());
		A = start.createPlayer();
		B = start.createPlayer();
		gameControl = new ConsoleInteractions();

		// load = new ShipLoader(gameRules);

	}

	/**
	 * 
	 * Metoda <code>gameInProgress</code> rozpoczyna gr� i jest odpowiedzialna
	 * za przebieg gry (od pocz�tku do ko�ca). *
	 * 
	 */
	public void gameInProgress() {
		gameControl.showMenu();

		A.setName(gameControl.scanName(1));
		B.setName(gameControl.scanName(2));

		setShips(A);
		setShips(B);

		BSPlayerStatus currentPlayer = A;
		BSPlayerStatus enemy = B;
		int[] cords = { 0, 0 };
		gameControl.showLegend();
		while (!isGameOver(enemy)) {

			BoardDrawing.drawGameBoardForOpponent(enemy.getPlansza());
			gameControl.showYourMove(currentPlayer);
			cords = pointRifle();
			if (gameRules.makeMove(enemy, cords[0], cords[1]) == 1) {
				gameControl.showMissMessage();
				enemy = currentPlayer;
				currentPlayer = changePlayer(currentPlayer);
			} else {
				gameControl.showHitMessage();
			}

			gameControl.showGameSummary(currentPlayer, enemy);
		}
		gameControl.showGameOver(currentPlayer);
		gameControl.showGameOver();
	}

	/**
	 * 
	 * Metoda <code>pointRifle</code> pobiera wsp�rz�dne do strza�u
	 *
	 * @return tablica int[], gdzie [0] to wsp�rz�dna x, a [1] to wsp�rz�dna y
	 */
	private int[] pointRifle() {
		int[] tab = { 0, 0 };

		while (true) {
			tab = gameControl.scanCoords();
			if (tab[0] >= 0 && tab[0] <= 9 && tab[1] >= 0 && tab[1] <= 9)
				return tab;
			else
				gameControl.showErrorMessage1();
		}
	}

	/**
	 * 
	 * Metoda <code>changePlayer</code> zmienia aktualnego gracza na
	 * przeciwnikia
	 *
	 * @param X
	 *            aktualny gracz l
	 * @return A lub B, czyli przeciwnika
	 */
	private BSPlayerStatus changePlayer(PlayerStatus X) {
		if (X.getName() == A.getName())
			return B;
		else
			return A;
	}

	/**
	 * 
	 * Metoda <code>isGameOver</code> pobiera dane do ustawienia statku
	 *
	 * @param X
	 *            gracz
	 * @return true, je�eli gra zosta�a zako�czona (wykonano wszystkie ruchy)
	 */
	private boolean isGameOver(BSPlayerStatus X) {
		if (X.getShipsNumber() > 0)
			return false;
		else
			return true;
	}

	/**
	 * 
	 * Metoda <code>setShips</code> pr�buje wy�o�a� metod�
	 * initializeShipsFromFile, w przypadku wyj�tku FileNotFoundException
	 * wywo�uje initializeShips
	 *
	 * @param gamer
	 *            gracz
	 */
	private void setShips(BSPlayerStatus gamer) {
		try {
			load.initializeShipsFromFile(gamer);
		} catch (FileNotFoundException e) {
			// load.initializeShips(gamer, gameControl, gameRules);
		}
	}

}
