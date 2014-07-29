package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
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
    JLabel status_label = new JLabel();

    private ImageIcon ready_icon = new ImageIcon(
	    ShipIcons.class.getResource("shoot.png"));
    private ImageIcon wait_icon = new ImageIcon(
	    ShipIcons.class.getResource("wait.png"));

    public ShootingPanel(IShootingPresenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initialize(int sizeH, int sizeV) {
	setLayout(new GridLayout(2, 2));
	playerBoardPanel = new BoardGraphicPanel(sizeH, sizeV);
	enemyBoardPanel = new BoardGraphicPanel(this, sizeH, sizeV);

	JLabel status_label = new JLabel(ready_icon);
	JButton change = new JButton("change");

	add(playerBoardPanel);
	add(enemyBoardPanel);
	add(status_label);
	add(change);

	change.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		changeStatus(false);
	    }
	});

	repaint();
    }

    @Override
    public void changeStatus(boolean ready) {
	if (ready == true)
	    status_label.setIcon(ready_icon);
	else
	    status_label.setIcon(wait_icon);
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
    public void disableBatlleBoardPlace(int x, int y) {
	enemyBoardPanel.disableButton(x, y);
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
