/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.factory;

import pl.praktykiatrem.game.battleship.PlayerStatus;

public class GameFactory {
    public PlayerStatus createPlayer()
    {
        return new PlayerStatus();
    }
}
