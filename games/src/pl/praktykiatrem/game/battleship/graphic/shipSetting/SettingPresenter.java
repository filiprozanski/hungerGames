package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

/**
 * presenter steruj¹cy widokiem do ustawiania statków na planszy
 * 
 * @author Filip Ró¿añski
 *
 */
public class SettingPresenter implements ISettingPresenter, IStageObserver {
    private int                    polesNumber;

    private int                    id;

    private Game                   gameRules;

    private PlayerStatus           player;

    private ISettingView           view;

    private ArrayList<Coordinates> locked;

    private IStageObserver         observer;

    public SettingPresenter(Game gameRules, PlayerStatus player, IStageObserver observer) {
        this.gameRules = gameRules;
        this.player = player;
        this.observer = observer;
        locked = new ArrayList<Coordinates>();
        view = new ShipSettingPanel(this);
        view.initialize(gameRules.getShipTypes(), gameRules.getBoardSize_H(),
                gameRules.getBoardSize_V(), this);
        view.changeStateAllBoardPlaces(false);
    }

    /**
     * zwraca panel do wyœwietlenia
     */
    @Override
    public ISettingView getView() {
        return view;
    }

    /**
     * tworzy listê zawieraj¹c¹ miejsca, na których ustawione zosta³y ju¿ statki
     */
    private void getLockedPlaces() {
        ArrayList<Coordinates> tab = new ArrayList<Coordinates>();

        for (int a = 0; a < gameRules.getShipsNumber(); a++)
            tab.addAll(gameRules.getCoordsList(player, a));

        this.locked = tab;
    }

    /**
     * funkcja wywo³ywana przy ka¿dym naciœniêciu przycisku reprezentuj¹cego statek
     * zapisuje informacje o nim do zmiennych globalnych, do wykorzystania przy ustawianiu
     * statków, kontroluje równie¿
     */
    @Override
    public void shipChoiceDone(int polesNumber, int id) {
        this.polesNumber = polesNumber;
        this.id = id;
        ArrayList<Coordinates> list = gameRules.getCoordsList(player, id);
        Coordinates[] tab = list.toArray(new Coordinates[list.size()]); // UWAGA!!!!
        if (!player.isShipSet(id)) {
            lockUsedPlaces();
        } else {
            view.disableAllBoardPlaces(tab[0].getX(), tab[0].getY());
        }
    }

    /**
     * odblokowuje wszystkie miejsca, po czym blokuje te znajduj¹ce siê na liœcie locked
     */
    private void lockUsedPlaces() {
        view.changeStateAllBoardPlaces(true);
        getLockedPlaces();
        for (Coordinates coord : locked)
            view.disableOneBoardPlace(coord.getX(), coord.getY());
    }

    /**
     * mechanizm ustawiania statku
     */
    @Override
    public void placeShip(int x, int y, int freq) {
        switch (freq) {
        case 1:
            firstClick(x, y);
            break;
        case 2:
            clearLastChoice(x, y, Direction.HORIZONTAL);
            secondClick(x, y);
            break;
        case 0:
            clearLastChoice(x, y, Direction.VERTICAL);
            view.changeButtonCallNumber(x, y, 1);
            break;
        }
    }

    /**
     * akcje wywolywane po pierwszym kliknieciu guzika
     * 
     * @param x
     *            wspolrzedna guzika
     * @param y
     */
    private void firstClick(int x, int y) {
        if (gameRules.placeShips(player, id, polesNumber, Direction.HORIZONTAL, x, y)) {
            drawOnBoard(x, y, Direction.HORIZONTAL, id + 1);
            view.changeButtonCallNumber(x, y, 2);
            view.setReadyButtonState(gameRules.getShipsNumber()
                    - gameRules.getActiveShipsNumber(player));
        } else {
            secondClick(x, y);
        }
    }

    /**
     * akcje wywolywane po drugim klikniêciu guzika
     * 
     * @param x
     *            wspo³rzêdna guzika
     * @param y
     */
    private void secondClick(int x, int y) {
        if (gameRules.placeShips(player, id, polesNumber, Direction.VERTICAL, x, y)) {
            drawOnBoard(x, y, Direction.VERTICAL, id + 1);
            view.changeButtonCallNumber(x, y, 0);
            view.setReadyButtonState(gameRules.getShipsNumber()
                    - gameRules.getActiveShipsNumber(player));
        } else {
            view.changeButtonCallNumber(x, y, 1);
        }

    }

    /**
     * czysci poprzednio ustawione miejsca
     * 
     * @param x
     *            wspolrzêdna guzika
     * @param y
     * @param dir
     *            kierunek ustawienia statku
     */
    public void clearLastChoice(int x, int y, Direction dir) {
        if (gameRules.displaceShips(player, id, polesNumber, dir, x, y)) {
            drawOnBoard(x, y, dir, 0);
            view.setReadyButtonState(gameRules.getShipsNumber()
                    - gameRules.getActiveShipsNumber(player));
            getLockedPlaces();
            for (Coordinates coord : locked)
                view.disableOneBoardPlace(coord.getX(), coord.getY());
        }
    }

    /**
     * rysuje statek w interfejsie graficznym
     * 
     * @param x
     *            wspó³rzêdna guzika
     * @param y
     * @param dir
     *            kieunek ustawienia statku
     * @param icon
     *            typ ikony reprezentuj¹cej statek
     */
    private void drawOnBoard(int x, int y, Direction dir, int icon) {
        if (dir == Direction.HORIZONTAL) {
            for (int i = 0; i < polesNumber; i++) {
                view.changePlaceIcon(x, y, icon);
                y++;
            }
        } else {
            for (int i = 0; i < polesNumber; i++) {
                view.changePlaceIcon(x, y, icon);
                x++;
            }
        }
    }

    @Override
    public void playerIsReady() {
        if (gameRules.getActiveShipsNumber(player) == gameRules.getShipsNumber()) {
            observer.playerIsReady();
            view.disableReadyButton();
        }
    }
}
