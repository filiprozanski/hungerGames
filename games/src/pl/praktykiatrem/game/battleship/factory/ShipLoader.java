package pl.praktykiatrem.game.battleship.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pl.praktykiatrem.game.battleship.console.BoardDrawing;
import pl.praktykiatrem.game.battleship.console.ConsoleInteractions;
import pl.praktykiatrem.game.battleship.console.ValidationInstruments;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;

/**
 * 
 * Klasa <code>ShipLoader</code> jest odpowiedzialna za �adowanie reprezantacji
 * statk�w do tablicy
 *
 * @author hungerGames
 *
 */
public class ShipLoader {
	private Game gameRules;

	/**
	 * 
	 * Konstruktor <code>ShipLoader</code> przypisuje referencj� na obiekt
	 * gameRules
	 *
	 * @param gameRules
	 *            obiekt gry z zaimplementowanymi regu�ami
	 */
	public ShipLoader(Game gameRules) {
		this.gameRules = gameRules;
	}

	/**
	 * 
	 * Metoda <code>initializeShipsFromFile</code> �aduje plik w kt�rym zawarta
	 * jest reprezentacja statk�w w formie [ilo��_maszt�w]
	 * [orientacja(pion/poziom)] [wsp�rz�dna_x] [wsp�rz�dna_y] nowa linia w
	 * pliku to nowy statek.
	 *
	 * @param gamer
	 *            gracz, w kt�rego tablicy b�dziemy ustawia� statki
	 */
	public void initializeShipsFromFile(PlayerStatus gamer)
			throws FileNotFoundException {
		File plik1 = new File("src/pl/praktykiatrem/game/battleship/files/"
				+ gamer.getName() + ".txt");
		Scanner odczyt = new Scanner(plik1);
		String temp;
		while (odczyt.hasNextLine()) {
			for (int i = 0; i < gamer.getShipsNumber(); i++) {
				temp = odczyt.nextLine().toUpperCase();
				gameRules.placeShips(gamer, i, gamer.getShipTypes(i),
						Direction.getDirection(temp.charAt(2)),
						(int) temp.charAt(4) - 48, (int) temp.charAt(6) - 48);

			}
		}
		odczyt.close();
	}

	/**
	 * 
	 * Metoda <code>initializeShips</code> �aduje plik w kt�rym zawarta jest
	 * reprezentacja statk�w w formie [ilo��_maszt�w] [orientacja(pion/poziom)]
	 * [wsp�rz�dna_x] [wsp�rz�dna_y] nowa linia w pliku to nowy statek.
	 *
	 * @param gamer
	 *            gracz, w kt�rego tablicy b�dziemy ustawia� statki
	 * @param gameControl
	 *            instancja interfejsu konsoli
	 * @param gameRules
	 *            instncja regu� gry
	 */
	public void initializeShips(PlayerStatus gamer,
			ConsoleInteractions gameControl, Game gameRules) {
		gameControl.showYourMove(gamer);

		for (int i = 0; i < gamer.getShipsNumber(); i++) {
			fetchShipCoords(gamer.getShipTypes(i), i, gamer, gameControl);
			// i++;
			// fetchShipCoords(gamer.getShipTypes(i), i, gamer, gameControl);
		}
	}

	/**
	 * 
	 * Metoda <code>fetchShipCoords</code> pobiera dane do ustawienia statku i
	 * ustawia statek.
	 *
	 * @param polesNumber
	 *            liczba maszt�w
	 * @param id
	 *            id statku kt�ry jest aktualnie ustawiamy
	 * @param gamer
	 *            gracz, kt�rego statki s� ustawiane
	 * @param gameControl
	 *            instancja interfejsu konsoli
	 */
	private void fetchShipCoords(int polesNumber, int id, PlayerStatus gamer,
			ConsoleInteractions gameControl) {
		Direction dir;
		int[] tab = { 0, 0 };

		while (true) {
			dir = gameControl.scanDirection(polesNumber);
			tab = gameControl.scanCoords();

			if (ValidationInstruments.isPlaceValid(dir, tab[0], tab[1],
					polesNumber)
					&& gameRules.placeShips(gamer, id, polesNumber, dir,
							tab[0], tab[1])) {
				BoardDrawing.drawGameBoardForPlayer(gamer.getPlansza());
				break;
			}
			gameControl.showErrorMessage1();
		}
	}

}
