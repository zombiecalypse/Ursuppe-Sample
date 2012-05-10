package com.acme.ursuppe.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;
import static com.acme.ursuppe.helpers.CollectionHelpers.getAll;

public class Square implements ISquare {
	private Map<Color, Integer> foodstuff = new HashMap<Color, Integer>();
	private IBoard board;

	@Override
	public Map<Color, Integer> getFoodstuff() {
		return new HashMap<Color, Integer>(foodstuff);
	}

	@Override
	public Collection<ISquare> getNeighbors() {
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
		if (board.from(this).get(moveDirection) == null) 
			return board.getNullSquare();
		
		return board.from(this).get(moveDirection);
	}

}
