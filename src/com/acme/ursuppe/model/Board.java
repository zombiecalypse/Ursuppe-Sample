package com.acme.ursuppe.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.acme.ursuppe.modules.SquareFactory;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;
import com.acme.ursuppe.types.Point;
import com.google.inject.Inject;

public class Board implements IBoard {
	
	@Override
	public String toString() {
		return "Board [squares=" + squares.size() + "]";
	}

	private final Map<Point, ISquare> squares = new HashMap<Point, ISquare>();
	
	@Inject
	public Board(Set<Point> points, SquareFactory sf) {
		for (Point point : points) {
			ISquare square = sf.make(this);
			squares.put(point, square);
			assert square.getBoard() == this;
			assert this.all().contains(square);
			
		}
	}
	
	private Point getPoint(ISquare s) {
		for (Entry<Point, ISquare> entry : squares.entrySet()) {
			if (entry.getValue().equals(s)) {
				return entry.getKey();
			}
		}
		assert false;
		return null;
	}
	
	private ISquare getSquare(Point p) {
		return squares.get(p);
	}

	@Override
	public Map<Direction, ISquare> from(ISquare square) {
		assert this.squares.containsValue(square);
		Map<Direction, ISquare> surrounding = new HashMap<Direction, ISquare>();
		Point position = getPoint(square);
		for (Direction direction : Arrays.asList(Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST)) {
			ISquare to = getSquare(position.in(direction));
			if (to != null && to.isEnterable()) {
				surrounding.put(direction, to);
			}
		}
		return surrounding;
	}

	@Override
	public Iterator<Entry<Point, ISquare>> iterator() {
		return new HashSet<Entry<Point, ISquare>>(this.squares.entrySet()).iterator();
	}

	@Override
	public Collection<ISquare> all() {
		return new HashSet<ISquare>(this.squares.values());
	}
}
