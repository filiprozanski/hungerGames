package pl.praktykiatrem.game.battleship.gameComponents;

import java.util.ArrayList;

/**
* reprezentuje statek ustawiany na planszy
* @author Filip R�zanski
*
*/
public class Ship {
private int shipID;
private int polesNumber;
/**
* przechowuje wszystkie koordynaty, na kt�rych statek jest umieszczony
*/
private ArrayList<Coordinates> coords;

// private Coordinate direction;

/**
*
* @param polesNumber liczba maszt�w tworzonego statku
*/
public Ship(int polesNumber) {
this.polesNumber = polesNumber;
coords = new ArrayList<Coordinates>(polesNumber);
}

/**
* zmniejsza liczbe maszt�w
* @return jezeli statek zostaje zatopiony zwraca true
*/
public boolean reducePolesNumber() {
polesNumber--;
if (polesNumber == 0)
return true;
else
return false;
}

/**
*
* @return liczba maszt�w
*/
public int getPolesNumber() {
return polesNumber;
}

/**
*
* @return id wybranego statku
*/
public int getID() {
return shipID;
}

/**
* Dodaje do listy koordynaty
* @param c dodawane koordynaty
*/
void addCoordinate(Coordinates c)
{
coords.add(c);
}
}