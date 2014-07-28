package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.ISettingView;

public class ShipSettingPanel extends JPanel implements IBoardPlaceObserver,
		ISettingView, IShipChoiceObserver {
	private BoardGraphicSettingPanel boardPanel;
	private ShipChoicePanel choicePanel;
	private ReadyButtonPanel readyPanel;
	private ISettingPresenter presenter;

	public ShipSettingPanel(ISettingPresenter presenter) {
		this.presenter = presenter;
		initialize();
	}

	private void initialize() {
		setLayout(new GridLayout(2, 2));
		boardPanel = new BoardGraphicSettingPanel(this);
		choicePanel = new ShipChoicePanel(this);
		readyPanel = new ReadyButtonPanel();

		add(boardPanel);
		add(choicePanel);
		add(readyPanel);
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
	
	public void choiceDone(int id, int polesNumber){
		presenter.shipChoiceDone(polesNumber, id);
	}

	@Override
	public void disableOneBoardPlace(int x, int y) {
		boardPanel.disableButton(x, y);
	}

	@Override
	public void disableAllBoardPlaces() {
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
				boardPanel.disableButton(i, j);
		}
	}

	@Override
	public void disableAllBoardPlaces(int x, int y) {
		disableAllBoardPlaces();
		boardPanel.enableButton(x, y);
	}

	@Override
	public void enableOneBoardPlace(int x, int y) {
		boardPanel.enableButton(x, y);
	}

	@Override
	public void enableAllBoardPlaces() {
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
				boardPanel.enableButton(i, j);
		}
	}
	
	public void enableShipButton(int id)
	{
		
	}
	
	public void disableShipButton(int id)
	{
		
	}
}
