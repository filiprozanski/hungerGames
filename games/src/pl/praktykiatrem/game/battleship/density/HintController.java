package pl.praktykiatrem.game.battleship.density;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;

public class HintController {
	private ComputerBoard board;
	private Game game;
	private int[] shipTypes;
	private int hitCounter = 0;
	private DensityController den;
	private boolean isHintOn;

	public HintController(Game game) {
		board = new ComputerBoard(game);
		this.game = game;
		shipTypes = game.getShipTypes();
		den = new DensityController(game, board);
		isHintOn = false;
	}

	public void setHint(boolean status) {
		den.updateDensityBoard(hitCounter, board);
		den.showHint(status);
		isHintOn = status;
	}

	public boolean isHintOn() {
		return isHintOn;
	}

	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			board.setSunk(arrayList.get(i).getX(), arrayList.get(i).getY());
		}
		shipTypes[id] = -1;
		// dlaczego tu jest +1 ? bo nie wywy�ujemy setHit jak statek jest
		// zatapiany
		hitCounter = hitCounter - arrayList.size() + 1;
		den.updateDensityBoard(hitCounter, board);
	}

	public void setHit(int x, int y) {
		board.setHit(x, y);
		this.hitCounter++;
		den.updateDensityBoard(hitCounter, board);
	}

	public void setMiss(int x, int y) {
		board.setMiss(x, y);
		den.updateDensityBoard(hitCounter, board);
	}
}