package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import pl.praktykiatrem.game.battleship.graphic.IStageObserver;

public interface ISettingView {
    public void changePlaceIcon(int x, int y, int type);

    public void disableOneBoardPlace(int x, int y);

    public void disableAllBoardPlaces();

    public void disableAllBoardPlaces(int x, int y); // pomija przy blokowaniu
    // pole podane w
    // argumentach

    public void enableOneBoardPlace(int x, int y);

    public void enableAllBoardPlaces();

    public void changeButtonCallNumber(int x, int y, int number);

    public void initialize(int[] tab, int sizeH, int sizeV,
	    IStageObserver observer);

    void disableReadyButton();
}