package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.listeners.UserStageListener;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;

/**
 * Klasa zawiera przyciski s³u¿±ce do nawigacji w trakcie ustawiania statków
 * Klasa <code>ButtonsPanel</code>
 *
 * @author filipr
 * @version 30 lip 2014 14:04:41
 *
 */
public class ButtonsPanel extends JPanel {
	/**
	 * Pole <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8608733676074348814L;

	/**
	 * przycisk oznaczaj±cy gotowo¶æ gracza do rozpoczêcia gry aktywny dopiero
	 * po ustawieniu wszystkich statków
	 */
	private JButton ready;

	/**
	 * przycisk do zrestartowania ustawienia statków
	 */
	private JButton reset;

	/**
	 * przycisk do losowego ustawienia statków przez komputer
	 */
	private JButton autoSet;

	private IStageObserver observer;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>ButtonsPanel</code>
	 *
	 * @param observer
	 */
	public ButtonsPanel(IStageObserver observer) {
		super(new GridLayout(3, 0));
		setBackground(new Color(135, 206, 235));
		this.observer = observer;
		initialize();
	}

	/**
	 * 
	 * Metoda <code>initialize</code>
	 * 
	 * inicjalizuje panel, dodaje do niego przyciski
	 *
	 * @param observer
	 */
	private void initialize() {
		ready = new JButton("Zacznij ustawiaæ statki!");
		reset = new JButton("Resetuj planszê!");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				observer.resetButtonClicked();
			}
		});
		autoSet = new JButton("Ustaw statki automatycznie!");
		autoSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				observer.randomButtonClicked();
			}
		});
		ready.addActionListener(new UserStageListener(observer));
		ready.setEnabled(false);
		add(ready, BorderLayout.CENTER);
		add(autoSet, BorderLayout.CENTER);
		add(reset, BorderLayout.CENTER);
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
	 * 
	 * Metoda <code>diableButton</code>
	 * 
	 * deaktywuje guzik pozwalaj±cy na przej¶cie do fazy strzelania
	 *
	 */
	public void diableButton() {
		ready.setEnabled(false);
		ready.setText("Czekam na drugiego gracza!");
	}

	/**
	 * 
	 * Metoda <code>setReadyButtonState</code> ... DOCUMENT ME!
	 *
	 * kontroluje stan przycisku ready
	 *
	 * @param state
	 *            (int) okre¶la liczbê statków pozosta³± do ustawienia, je¶li 0,
	 *            to przycisk jest blokowany
	 */
	public void setReadyButtonState(int state) {
		if (state == 0) {
			ready.setEnabled(true);
			ready.setText("GOTOWY!");
		} else {
			ready.setEnabled(false);
			ready.setText("Pozosta³o statków: " + state);
		}
	}
}
