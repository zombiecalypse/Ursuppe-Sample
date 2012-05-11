package com.acme.ursuppe.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;
import static com.acme.ursuppe.helpers.Literals.*;
import static com.acme.ursuppe.helpers.CollectionHelpers.*;


public class Square implements ISquare {
	public Square(IBoard board2) {
		this.board = board2;
	}

	@Override
	public String toString() {
		return "Square [foodstuff=" + foodstuff + ", amoebas=" + amoebas.size()
				+ ", board=" + board + "]";
	}

	private final Map<Color, Integer> foodstuff = 
			map(Color.RED, 0)
			.and(Color.BLUE, 0)
			.and(Color.YELLOW, 0).done();
	private final Map<Color, Set<IAmoeba>> amoebas = 
			map(Color.RED, (Set<IAmoeba>) new HashSet<IAmoeba>())
			.and(Color.BLUE, new HashSet<IAmoeba>())
			.and(Color.YELLOW, new HashSet<IAmoeba>()).done();
	
	public final IBoard board;

	@Override
	public Map<Color, Integer> getFoodstuff() {
		return new HashMap<Color, Integer>(foodstuff);
	}

	@Override
	public Collection<ISquare> getNeighbors() {
		assert board != null : String.format("%s has no board", this);
		return getAll(board.from(this), Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
	}

	@Override
	public void removeFoodStuff(Color c) {
		assert foodstuff.get(c) != null && foodstuff.get(c) > 0 : "Can't remove foodstuff, because it's unavailable"; 
		foodstuff.put(c, foodstuff.get(c) - 1);
	}

	@Override
	public void addFoodStuff(Color c) {
		assert foodstuff.get(c) != null : "Can't add foodstuff of which I've never heard";
		foodstuff.put(c, foodstuff.get(c) + 1);
	}

	@Override
	public Direction getDirectionOf(ISquare neighbor) {
		for (Entry<Direction, ISquare> entry : board.from(this).entrySet()) {
			if (entry.getValue().equals(neighbor)) return entry.getKey();
		}
		assert false : String.format("%s is not neighbor of %s", neighbor, this);
		return null;
	}

	@Override
	public ISquare getInDirection(Direction moveDirection) {
		assert board != null;
		if (board.from(this).get(moveDirection) == null) 
			return this;
		
		return board.from(this).get(moveDirection);
	}

	@Override
	public boolean isEnterable() {
		return true;
	}

	@Override
	public Collection<IAmoeba> getAmoebas() {
		return combine(getAll(amoebas, Color.RED, Color.BLUE, Color.YELLOW));
	}

	@Override
	public void enter(IAmoeba amoeba) {
		this.amoebas.get(amoeba.getColor()).add(amoeba);
	}
	@Override
	public void leave(IAmoeba amoeba) {
		assert this.amoebas.get(amoeba.getColor()).contains(amoeba) : String.format("%s never entered this square", amoeba);
		this.amoebas.get(amoeba.getColor()).remove(amoeba);
	}

	@Override
	public IBoard getBoard() {
		return board;
	}
}
