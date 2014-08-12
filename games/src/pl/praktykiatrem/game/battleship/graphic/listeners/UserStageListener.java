package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.praktykiatrem.game.battleship.graphic.observers.ISettingButtonsObserver;

public class UserStageListener implements ActionListener {
	private ISettingButtonsObserver observer;

	public UserStageListener(ISettingButtonsObserver observer) {
		this.observer = observer;
	}

	public void actionPerformed(ActionEvent event) {
		observer.playerIsReady();
	}
}
