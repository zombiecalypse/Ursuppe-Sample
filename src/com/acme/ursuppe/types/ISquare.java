package com.acme.ursuppe.types;

import java.util.Collection;
import java.util.Map;


public interface ISquare {

	Map<Color, Integer> getFoodstuff();

	Collection<ISquare> getNeighbors();

	void removeFoodStuff(Color c);

	void addFoodStuff(Color c);
}
