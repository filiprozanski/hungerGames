package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;

public interface ISettingView {
	public void changePlaceIcon(int x, int y, int type);

	public void disableOneBoardPlace(int x, int y);

	public void changeStateAllBoardPlaces(boolean enable);

	public void disableAllBoardPlaces(int x, int y); // pomija przy blokowaniu
	// pole podane w
	// argumentach

	public void enableOneBoardPlace(int x, int y);

	public void changeButtonCallNumber(int x, int y, int number);

	public void initialize(int[] tab, int sizeH, int sizeV,
			IStageObserver observer);

	void disableReadyButton();

	void setReadyButtonState(int state);

	public void setOkIconShipButton(int id, boolean ok);
}