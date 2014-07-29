package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;

public class UserStageListener implements ActionListener {
    private IStageObserver observer;

    public UserStageListener(IStageObserver observer) {
	this.observer = observer;
    }

    public void actionPerformed(ActionEvent event) {
	observer.playerIsReady();
    }
}
