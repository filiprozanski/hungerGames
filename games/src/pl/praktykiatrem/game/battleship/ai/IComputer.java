package pl.praktykiatrem.game.battleship.ai;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;

public interface IComputer {

	public abstract Coordinates getCords();

	public abstract void setSink(int id, ArrayList<Coordinates> arrayList);

	public abstract void setHit(Coordinates coords);

	public abstract void setMiss(Coordinates coords);

}