/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.factory;

import pl.praktykiatrem.game.battleship.Player;

public class GameFactory {
    public Player createPlayer()
    {
        return new Player();
    }
}
