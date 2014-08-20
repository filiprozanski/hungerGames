package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlace;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.uniElements.PlayerStatus;

public class ShootingPlayerHandler {

	private BSPlayerStatus player;

	private IShootingPresenterControll pres;

	private Game g;

	private IShootingController supervisor;

	public ShootingPlayerHandler(BSPlayerStatus player, boolean status,
			IShootingController supervisor) {
		this.player = player;
		this.supervisor = supervisor;
		pres = new ShootingPresenter(g, player, supervisor);
		pres.setStats(g.getShipsNumber(), g.getShipsNumber());
		pres.changeStatus(status);
		pres.showFrame();
	}

	public boolean makeMove(BSPlayerStatus opponent, int x, int y) {
		int result = g.makeMove(opponent, x, y);
		if (result >= 1) {
			supervisor.boardSettingHit(player, opponent, x, y);
			if (result == 2) {
				supervisor.boardSettingSink(player, opponent, x, y);
			}
			return true;
		} else {
			supervisor.boardSettingMiss(player, opponent, x, y);
			return false;
		}
	}

	public void callMenu() {
		pres.closeFrame();
	}

	public void resign(PlayerStatus player) {
		if (player.equals(this.player))
			pres.gameOver(true);
		else
			pres.gameOver(false);
		supervisor.drawLeftShips1();
		supervisor.drawLeftShips2();
		pres.changeGiveUpButtonLabel();
	}

	public void drawLeftShips(BSPlayerStatus opponent) {
		for (int j = 0; j < g.getBoardSizeH(); j++)
			for (int i = 0; i < g.getBoardSizeV(); i++) {
				BSPlace place = (BSPlace) opponent.getPlace(i, j);
				if (place.isShipOnPlace() && place.isPlaceInGame())
					pres.fchangeIcon(i, j, place.getShipId() + 1);
			}
	}

	public void gameOver(PlayerStatus player) {
		if (player.equals(this.player)) {
			supervisor.drawLeftShips1();
			pres.gameOver(true);
		} else {
			supervisor.drawLeftShips2();
			pres.gameOver(false);
		}
		pres.changeGiveUpButtonLabel();
	}
}
