package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
import pl.praktykiatrem.game.battleship.graphic.buttons.StressButton;

public class InfoPanel extends JPanel {
    private JLabel     statusLabel = new JLabel();

    private StatsPanel stats       = new StatsPanel();

    private ImageIcon  ready_icon  = new ImageIcon(ShipIcons.class.getResource("shoot.png"));

    private ImageIcon  wait_icon   = new ImageIcon(ShipIcons.class.getResource("wait.png"));

    public InfoPanel() {
        super(new GridLayout(3, 0));
        setBackground(new Color(135, 206, 235));
        initialize();
    }

    private void initialize() {

        statusLabel.setIcon(ready_icon);

        JButton giveUp = new JButton("Poddaj siê");
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        buttonPanel.setBackground(new Color(135, 206, 235));
        buttonPanel.add(new StressButton());
        buttonPanel.add(giveUp);

        add(stats);
        add(statusLabel);
        add(buttonPanel);

    }

    public Dimension gerPrefferedSize() {
        return new Dimension(150, 150);
    }

    public Dimension gerMinumumSize() {
        return new Dimension(150, 150);
    }

    public void changeStatus(boolean ready) {
        if (ready == true)
            statusLabel.setIcon(ready_icon);
        else
            statusLabel.setIcon(wait_icon);
    }

    public void setStats(int playerShips, int enemyShips, int accuracy) {
        stats.setLblPlayerShipsNumber(Integer.toString(playerShips));
        stats.setLblEnemyShipsNumber(Integer.toString(enemyShips));
        stats.setLblAccuracyNumber(Integer.toString(accuracy) + "%");
    }

    public void setStats(int playerShips, int enemyShips) {
        stats.setLblPlayerShipsNumber(Integer.toString(playerShips));
        stats.setLblEnemyShipsNumber(Integer.toString(enemyShips));
    }
}