package com.acme.ursuppe.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;

public class Board implements IBoard {
	static class Point {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point in(Direction direction) {
			return new Point(direction.x()+x, direction.y()+y);
		}
	}
	private Map<Point, ISquare> squares;
	
	public Board(Map<Point, ISquare> squares) {
		this.squares = squares;
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
		Map<Direction, ISquare> surrounding = new HashMap<Direction, ISquare>();
		Point position = getPoint(square);
		for (Direction direction : Arrays.asList(Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST)) {
			surrounding.put(direction, getSquare(position.in(direction)));
		}
		return surrounding;
	}

	@Override
	public ISquare getNullSquare() {
		return new NullSquare();
	}

}
