package pl.praktykiatrem.game.battleship.ai;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.density.DensityController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class Hard implements IComputer {
	private ComputerBoard board;
	private Game game;
	private int shotCounter = 0;
	private int BoardH;
	private int BoardV;
	private int[] shipTypes;
	private int hitCounter = 0;
	private DensityController den;

	public Hard(Game game) {
		this.board = new ComputerBoard(game);
		this.game = game;
		this.BoardH = game.getBoardSizeH();
		this.BoardV = game.getBoardSizeV();
		this.shipTypes = game.getShipTypes();
		this.den = new DensityController(game, board);
		// debug
		// den.showHint(true);
	}

	@Override
	public Coordinates getCords() {
		if (shotCounter == 0) {
			den.updateDensityBoard(hitCounter, board);
		}
		shotCounter++;
		return den.getCords(board);
	}

	@Override
	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			board.setSunk(new Coordinates(arrayList.get(i).getX(), arrayList
					.get(i).getY()));
		}
		shipTypes[id] = -1;
		// dlaczego tu jest +1 ? bo nie wywy³ujemy setHit jak statek jest
		// zatapiany
		hitCounter = hitCounter - arrayList.size() + 1;
		den.updateDensityBoard(hitCounter, board);
	}

	@Override
	public void setHit(Coordinates coords) {
		board.setHit(coords);
		this.hitCounter++;
		den.updateDensityBoard(hitCounter, board);
	}

	@Override
	public void setMiss(Coordinates coords) {
		board.setMiss(coords);
		den.updateDensityBoard(hitCounter, board);
	}
}