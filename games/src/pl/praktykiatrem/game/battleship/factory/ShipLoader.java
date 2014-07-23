package pl.praktykiatrem.game.battleship.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pl.praktykiatrem.game.battleship.console.BoardDrawing;
import pl.praktykiatrem.game.battleship.console.ConsoleInteractions;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.ValidationInstruments;

public class ShipLoader {
	private Game gameRules;

	public ShipLoader(Game gameRules) {
		this.gameRules = gameRules;
	}

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
	 * Metoda <code>fetchShipCoords</code> pobiera dane do ustawienia statku
	 *
	 * @param polesNumber
	 *            liczba masztów
	 * @param id
	 * @param gamer
	 *            gracz, którego statki s± ustawiane
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
