package com.acme.ursuppe.types;

import java.util.Collection;
import java.util.Map;

import com.acme.ursuppe.model.Direction;


public interface ISquare {

	Map<Color, Integer> getFoodstuff();

	Collection<ISquare> getNeighbors();

	void removeFoodStuff(Color c);

	void addFoodStuff(Color c);

	Direction getDirectionOf(ISquare neighbors);

	ISquare getInDirection(Direction moveDirection);
}
