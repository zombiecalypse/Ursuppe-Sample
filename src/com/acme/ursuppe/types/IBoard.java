package com.acme.ursuppe.types;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import com.acme.ursuppe.model.Direction;

public interface IBoard extends Iterable<Entry<Point, ISquare>>{

	Map<Direction, ISquare> from(ISquare square);
	
	Collection<ISquare> all();
}
