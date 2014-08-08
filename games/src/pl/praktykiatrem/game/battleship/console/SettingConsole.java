package pl.praktykiatrem.game.battleship.console;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingView;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.PlayerStatus;

public class SettingConsole implements ISettingView {
	private ISettingPresenter presenter;
	private PlayerStatus player;
	private Game gameRules;
	private ConsoleInteractions console;

	public SettingConsole(ISettingPresenter presenter, PlayerStatus player,
			Game gameRules) {
		this.presenter = presenter;
		this.player = player;
		this.gameRules = gameRules;
		console = new ConsoleInteractions();
	}

	@Override
	public void changePlaceIcon(int x, int y, int type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableOneBoardPlace(int x, int y) {
		BoardDrawing.drawGameBoardForPlayer(player.getPlansza());
	}

	@Override
	public void changeStateAllBoardPlaces(boolean enable) {
		if (enable)
			BoardDrawing.drawGameBoardForPlayer(player.getPlansza());
		else
			System.out.println("Wait");
	}

	@Override
	public void disableAllBoardPlaces(int x, int y) {
		System.out.println("Wait");

	}

	@Override
	public void enableOneBoardPlace(int x, int y) {
		BoardDrawing.drawGameBoardForPlayer(player.getPlansza());
	}

	@Override
	public void changeButtonCallNumber(int x, int y, int number) {
		// funckja nieu퓓wana
	}

	@Override
	public void initialize(int[] tab, int sizeH, int sizeV) {
		// funckja nieu퓓wana
	}

	@Override
	public void disableReadyButton() {
		// funckja nieu퓓wana

	}

	@Override
	public void setReadyButtonState(int state) {
		// funckja nieu퓓wana

	}

	@Override
	public void setOkIconShipButton(int id, boolean ok) {
		// funckja nieu퓓wana

	}

	@Override
	public void showFrame(String name) {
		initializeShips();
	}

	@Override
	public void closeFrame() {
		System.out.println("Koniec ustawiania");
	}

	public void initializeShips() {
		console.showYourMove(player);

		for (int i = 0; i < gameRules.getShipsNumber(); i++) {
			presenter.shipChoiceDone(gameRules.getShipType(i), i);
			fetchShipCoords(gameRules.getShipType(i), i);
		}
	}

	private void fetchShipCoords(int polesNumber, int id) {
		Direction dir;
		int[] tab = { 0, 0 };

		dir = console.scanDirection(polesNumber);
		tab = console.scanCoords();

		if (dir == Direction.HORIZONTAL)
			presenter.placeShip(tab[0], tab[1], 1);
		else if (dir == Direction.VERTICAL)
			presenter.placeShip(tab[0], tab[1], 2);
	}

}
