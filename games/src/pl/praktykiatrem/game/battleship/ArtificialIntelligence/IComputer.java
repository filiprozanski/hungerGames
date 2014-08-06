package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

public interface IComputer {

	public abstract Coordinates getCords(boolean hit);

	public abstract void setSink(int id, ArrayList<Coordinates> arrayList);

	public abstract void setHit(int x, int y);

	public abstract void setMiss(int x, int y);

}