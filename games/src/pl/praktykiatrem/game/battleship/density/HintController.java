package pl.praktykiatrem.game.battleship.density;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.ai.ComputerBoard;
import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.rules.GameRules;

public class HintController {
	private ComputerBoard board;
	private int[] shipTypes;
	private int hitCounter = 0;
	private DensityController den;
	private boolean isHintOn;

	public HintController(GameRules game) {
		board = new ComputerBoard(game);
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
			board.setSunk(arrayList.get(i));
		}
		shipTypes[id] = -1;
		// dlaczego tu jest +1 ? bo nie wywy³ujemy setHit jak statek jest
		// zatapiany
		hitCounter = hitCounter - arrayList.size() + 1;
		den.updateDensityBoard(hitCounter, board);
	}

	public void setHit(Coordinates coords) {
		board.setHit(coords);
		this.hitCounter++;
		den.updateDensityBoard(hitCounter, board);
	}

	public void setMiss(Coordinates coords) {
		board.setMiss(coords);
		den.updateDensityBoard(hitCounter, board);
	}
}
