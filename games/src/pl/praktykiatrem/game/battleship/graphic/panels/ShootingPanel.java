package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.IShootingView;

public class ShootingPanel extends JPanel implements IBoardPlaceObserver,
	IShootingView {

    private BoardGraphicPanel playerBoardPanel;
    private BoardGraphicPanel enemyBoardPanel;
    private ButtonsPanel buttonPanel;
    private IShootingPresenter presenter;

    public ShootingPanel(IShootingPresenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initialize(int sizeH, int sizeV) {
	setLayout(new GridLayout(2, 2));
	playerBoardPanel = new BoardGraphicPanel(sizeH, sizeV);
	enemyBoardPanel = new BoardGraphicPanel(this, sizeH, sizeV);

	add(playerBoardPanel);
	add(enemyBoardPanel);

	repaint();
    }

    @Override
    public void changePlaceIcon(int x, int y, int type) {
	playerBoardPanel.changePlaceIcon(x, y, type);
    }

    @Override
    public void changeBattlePlaceIcon(int x, int y, int type) {
	enemyBoardPanel.changePlaceIcon(x, y, type);
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
    public void disableAllPlayerBoardPlaces() {
	playerBoardPanel.disableAllButtons();
    }

    @Override
    public void disableAllEnemyBoardPlaces() {
	enemyBoardPanel.disableAllButtons();
    }

    @Override
    public void enableAllEnemyBoardPlaces() {
	enemyBoardPanel.enableAllButtons();
    }

    @Override
    public void drawShipLocation(Coordinates[] tab, int id) {
	for (Coordinates coord : tab)
	    changePlaceIcon(coord.getX(), coord.getY(), id + 1);
    }

    @Override
    public void clicked(int x, int y, int freq) {
	// TODO Auto-generated method stub
	presenter.shot(x, y);
    }

}
