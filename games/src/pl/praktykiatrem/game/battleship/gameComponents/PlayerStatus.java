/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 * 
 * Copyright ATREM S.A. ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.gameComponents;

import java.util.ArrayList;

/**
 * Klasa odpowadia za przchowywanie informacji o statusie gracza m.in. liczbie stakow.
 * Przechowuje tez jego plansze, oraz tablice statków
 * 
 * @author Filip Ró¿añski
 *
 */
public class PlayerStatus {
    private Board       plansza;

    private Ship[]      ships;

    private int         shipTypes[];

    private int         shipsNumber;

    private Player      gamer;

    private int         playerID;

    private static int  counter = 100001;

    private PlayerStats stats;

    // private String name;

    /**
     * 
     * @param boardSize_x
     *            liczba miejsc planszy w poziome
     * @param boardSize_y
     *            liczba miejsc planszy w pionie
     * @param shipsNumber
     *            liczba statków gracza pozosta³a do zbicia na planszy
     * @param shipTypes
     *            tablica przechowuj¹ca informacje o typie statków u¿ywanych w rozgrywce
     */
    public PlayerStatus(int boardSizeH, int boardSizeV, int[] shipTypes) {
        this.shipsNumber = 0;
        this.shipTypes = shipTypes;
        playerID = counter++;
        plansza = new Board(boardSizeH, boardSizeV);
        ships = new Ship[shipTypes.length];
        for (int i = 0; i < shipTypes.length; i++)
            setShip(i, shipTypes[i]);
        gamer = new Player();
        stats = new PlayerStats();
    }

    /**
     * Porównuje graczy wykorzystuj¹c do tego nadane im ID
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PlayerStatus))
            return false;

        PlayerStatus other2 = (PlayerStatus) other;
        if (this.playerID == other2.playerID)
            return true;
        else
            return false;
    }

    /**
     * 
     * @return imiê gracza
     */
    public String getName() {
        return gamer.getName();
    }

    /**
     * Ustawia imiê gracza
     * 
     * @param name
     */
    public void setName(String name) {
        gamer.setName(name);
    }

    /**
     * 
     * @return liczbe statków gracza pozosta³ych na planszy
     */
    public int getShipsNumber() {
        return shipsNumber;
    }

    /**
     * zwiêksza liczbê statków o jeden
     */
    public void increaseShipsNumber() {
        shipsNumber++;
    }

    /**
     * zmmniejsza liczbê statków o jeden
     */
    public void decreaseShipsNumber() {
        if (shipsNumber > 0)
            shipsNumber--;
    }

    /**
     * 
     * @return zwraca plansze gracza
     */
    public Board getPlansza() {
        return plansza;
    }

    /**
     * zmniejsza liczbê statków po zbiciu o 1
     */
    public void reduceShipsNumber() {
        shipsNumber--;
    }

    /**
     * 
     * @param id
     * @param polesNumber
     *            liczba masztów statku
     */
    public void setShip(int id, int polesNumber) {
        ships[id] = new Ship(polesNumber);
    }

    /**
     * 
     * @param id
     * @return statek o podanym id
     */
    public Ship getShip(int id) {
        return ships[id];
    }

    /**
     * 
     * @param id
     * @return statek o podanym id
     */
    public boolean isShipSet(int id) {
        return ships[id].isShipSet();
    }

    /**
     * deaktywuje na planszy miejsce opisane parametrami
     * 
     * @param x
     *            wspó³rzêdna pozioma
     * @param y
     *            wspó³rzêdna pionowa
     */
    public void takeOutShip(int x, int y) {
        plansza.takeOut(x, y);
    }

    /**
     * 
     * @param i
     * @return zwraca liczbe masztów statku znajduj¹cego siê w tablicy pod indeksem i
     */
    public int getShipTypes(int i) {
        return shipTypes[i];
    }

    /**
     * zmniejsza liczbe masztów o jeden
     * 
     * @param x
     * @param y
     */
    public boolean reducePolesNumber(int x, int y) {
        Place p = plansza.getPlace(x, y);
        int shipID = p.getShipId();
        return removeCoordinate(shipID, new Coordinates(x, y));
    }

    /**
     * dodaje koordynaty do listy znajduj¹cej siê w statku
     * 
     * @param ID
     * @param c
     */
    public void addCoordinates(int ID, Coordinates c) {
        ships[ID].addCoordinate(c);
    }

    /**
     * usuwa podane koordynaty z listy znajduj¹cej siê w statku
     * 
     * @param ID
     *            numer identzfikaczjnz statku
     * @param c
     *            koordynaty do usuniêcia
     */
    public boolean removeCoordinate(int ID, Coordinates c) {
        return ships[ID].removeCoordinate(c);
    }

    /**
     * 
     * @param ID
     *            numer identyfikacyjny statku
     * @return listê koordynat, które zajmuje dany statek
     */
    public ArrayList<Coordinates> getCoords(int ID) {
        return ships[ID].getCoordsTable();
    }

    /**
     * 
     * @param ID
     *            numer identyfikacyjny statku
     * @return tablice koordynat, które zajmuje dany statek
     */
    public Coordinates[] getCoordsTable(int ID) {
        ArrayList<Coordinates> coordsList = ships[ID].getCoordsTable();
        return coordsList.toArray(new Coordinates[coordsList.size()]);
    }

    public int getAccuracy(boolean hit) {
        return stats.getAccuracy(hit);
    }

}
