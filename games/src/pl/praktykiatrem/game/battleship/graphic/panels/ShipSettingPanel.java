package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.IStageObserver;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;
import pl.praktykiatrem.game.battleship.graphic.observers.IShipChoiceObserver;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.ISettingView;

public class ShipSettingPanel extends JPanel implements IBoardPlaceObserver,
	ISettingView, IShipChoiceObserver {

    private BoardGraphicPanel boardPanel;
    private ShipChoicePanel choicePanel;
    private ButtonsPanel readyPanel;
    private ISettingPresenter presenter;

    public ShipSettingPanel(ISettingPresenter presenter) {

	this.presenter = presenter;
    }

    public void initialize(int[] shipTypes, int sizeH, int sizeV,
	    IStageObserver observer) {
	setLayout(new GridLayout(2, 2));
	boardPanel = new BoardGraphicPanel(this, sizeH, sizeV);
	choicePanel = new ShipChoicePanel(this, shipTypes);
	readyPanel = new ButtonsPanel(observer);

	add(boardPanel);
	add(choicePanel);
	add(readyPanel);

	repaint();
    }

    @Override
    public void changePlaceIcon(int x, int y, int type) {
	boardPanel.changePlaceIcon(x, y, type);
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(660, 660);
    }

    @Override
    public Dimension getMinimumSize() {
	return new Dimension(660, 660);
    }

    @Override
    public void clicked(int x, int y, int freq) {
	presenter.placeShip(x, y, freq);
    }

    public void choiceDone(int id, int polesNumber) {
	presenter.shipChoiceDone(polesNumber, id);
    }

    @Override
    public void disableOneBoardPlace(int x, int y) {
	boardPanel.disableButton(x, y);
    }

    @Override
    public void disableAllBoardPlaces() {
	boardPanel.disableAllButtons();
    }

    @Override
    public void disableAllBoardPlaces(int x, int y) {
	boardPanel.disableAllButtons();
	boardPanel.enableButton(x, y);
    }

    @Override
    public void enableOneBoardPlace(int x, int y) {
	boardPanel.enableButton(x, y);
    }

    @Override
    public void enableAllBoardPlaces() {
	boardPanel.enableAllButtons();
    }

    public void enableShipButton(int id) {
	choicePanel.enableShipButton(id);
    }

    public void disableShipButton(int id) {
	choicePanel.disableShipButton(id);
    }

    public void changeButtonCallNumber(int x, int y, int number) {
	boardPanel.changeButtonCallNumber(x, y, number);
    }

    @Override
    public void disableReadyButton() {
	readyPanel.diableButton();
    }
}
