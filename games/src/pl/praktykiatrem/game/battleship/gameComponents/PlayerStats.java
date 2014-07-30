/*
 * Plik stworzony dnia 30 lip 2014 przez filipr
 * 
 * Copyright ATREM S.A. ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

public class PlayerStats {
    private float shots;

    private float hits;

    private int   accuracy;

    PlayerStats() {
        shots = 0f;
        hits = 0f;
        accuracy = 0;
    }

    public int getAccuracy(boolean hit) {
        if (hit)
            hits++;
        shots++;
        accuracy = (int) ((hits / shots) * 100);

        return accuracy;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getShots() {
        return (int) shots;
    }

    public int getHits() {
        return (int) hits;
    }
}
