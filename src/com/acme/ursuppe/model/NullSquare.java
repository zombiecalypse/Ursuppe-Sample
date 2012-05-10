package com.acme.ursuppe.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.ISquare;

public class NullSquare implements ISquare {

	@Override
	public String toString() {
		return "NullSquare";
	}

	@Override
	public Map<Color, Integer> getFoodstuff() {
		return new HashMap<Color, Integer>();
	}

	@Override
	public Collection<ISquare> getNeighbors() {
		return new HashSet<ISquare>();
	}

	@Override
	public void removeFoodStuff(Color c) {
		assert false : "Can't remove food from NullSquare";
	}

	@Override
	public void addFoodStuff(Color c) {
		assert false : "Can't add food to NullSquare";
	}

	@Override
	public Direction getDirectionOf(ISquare neighbors) {
		assert false : "NullSquare has not "+neighbors + " as neighbor";
		return null;
	}

	@Override
	public ISquare getInDirection(Direction moveDirection) {
		return this;
	}

}
