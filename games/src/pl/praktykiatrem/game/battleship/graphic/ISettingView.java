package pl.praktykiatrem.game.battleship.graphic;

public interface ISettingView {
	public void changePlaceIcon(int x, int y, int type);
	public void disableOneBoardPlace(int x, int y);
	public void disableAllBoardPlaces();
	public void disableAllBoardPlaces(int x, int y); //pomija przy blokowaniu pole podane w argumentach
	public void enableOneBoardPlace(int x, int y);
	public void enableAllBoardPlaces();
	public void enableShipButton(int id);
	public void disableShipButton(int id);
	public void changeButtonCallNumber(int x, int y);
	public void initialize(int[] tab, int sizeH, int sizeV);
}
