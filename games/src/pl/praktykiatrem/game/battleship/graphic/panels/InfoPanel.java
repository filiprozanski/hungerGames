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

/**
 *
 * Klasa <code>InfoPanel</code>
 *
 * Klasa reprezentuje panel, który przechowuje informacje dla gracza potrzebne w
 * trakcie gry
 *
 * @author wiktorm
 * @version 30 lip 2014 14:02:46
 *
 */
public class InfoPanel extends JPanel {
	/**
	 * Pole <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 8302437826968529957L;
	/**
	 * label reprezentuj±cy ikony poni¿ej nie s±dzê, ¿eby to by³o najlepsze
	 * rozwi±zanie
	 */
	private JLabel statusLabel = new JLabel();
	/**
	 * panel ze statystykami
	 */
	private StatsPanel stats = new StatsPanel();
	/**
	 * ikona ruchu
	 */
	private ImageIcon ready_icon = new ImageIcon(
			ShipIcons.class.getResource("shoot.png"));
	/**
	 * ikona oczekiwania na ruch
	 */
	private ImageIcon wait_icon = new ImageIcon(
			ShipIcons.class.getResource("wait.png"));

	private ImageIcon win_icon = new ImageIcon(
			ShipIcons.class.getResource("win1.png"));

	private ImageIcon lose_icon = new ImageIcon(
			ShipIcons.class.getResource("lose1.png"));

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>InfoPanel</code>
	 *
	 */
	public InfoPanel() {
		super(new GridLayout(3, 0));
		setBackground(new Color(135, 206, 235));
		initialize();
	}

	/**
	 * Inicjalizuje okno z informacjami o grze Metoda <code>initialize</code>
	 *
	 */
	private void initialize() {

		statusLabel.setIcon(ready_icon);

		JButton giveUp = new JButton("Poddaj siê");
		giveUp.setBackground(new Color(135, 206, 235));
		JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
		buttonPanel.setBackground(new Color(135, 206, 235));
		buttonPanel.add(new StressButton());
		buttonPanel.add(giveUp);

		add(stats);
		add(statusLabel);
		add(buttonPanel);

	}

	/**
	 * 
	 * Metoda <code>gerPrefferedSize</code>
	 *
	 * @return optymalny rozmiar panelu
	 */
	public Dimension gerPrefferedSize() {
		return new Dimension(150, 150);
	}

	/**
	 * 
	 * Metoda <code>gerMinumumSize</code>
	 *
	 * @return minimalny rozmiar panelu
	 */
	public Dimension gerMinumumSize() {
		return new Dimension(150, 150);
	}

	/**
	 * zmienia informacje, o przebiegu rozgrywki <code>changeStatus</code>!
	 *
	 * @param state
	 *            1 - gotowy do strza³u, 0 - czekaj na strza³ przeciwnika, 2 -
	 *            przegra³e¶, 3 - wygra³es
	 */
	public void changeStatus(int state) {
		if (state == 1)
			statusLabel.setIcon(ready_icon);
		else if (state == 0)
			statusLabel.setIcon(wait_icon);
		else if (state == 2)
			statusLabel.setIcon(lose_icon);
		else if (state == 3)
			statusLabel.setIcon(win_icon);
	}

	/**
	 * aktualizuje pole ze statystykami gracza Metoda <code>setStats</code>
	 *
	 * @param playerShips
	 *            liczba statków gracza
	 * @param enemyShips
	 *            liczba statków przeciwnika
	 * @param accuracy
	 *            skuteczno¶æ oddawanych strza³ów
	 */
	public void setStats(int playerShips, int enemyShips, int accuracy) {
		stats.setLblPlayerShipsNumber(Integer.toString(playerShips));
		stats.setLblEnemyShipsNumber(Integer.toString(enemyShips));
		stats.setLblAccuracyNumber(Integer.toString(accuracy) + "%");
	}

	/**
	 * aktualizuje pole ze statystykami gracza Metoda <code>setStats</code>
	 *
	 * @param playerShips
	 *            liczba statków gracza
	 * @param enemyShips
	 *            liczba statków przeciwnika
	 */
	public void setStats(int playerShips, int enemyShips) {
		stats.setLblPlayerShipsNumber(Integer.toString(playerShips));
		stats.setLblEnemyShipsNumber(Integer.toString(enemyShips));
	}
}