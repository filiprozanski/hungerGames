package pl.praktykiatrem.game.battleship.graphic.shipSetting;


public interface ISettingController {

	void playerIsReady();

	void playerIsNotReady();

	ISettingView getView(int i);

}
