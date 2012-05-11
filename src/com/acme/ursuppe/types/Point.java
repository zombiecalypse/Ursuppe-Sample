package com.acme.ursuppe.types;

import com.acme.ursuppe.model.Direction;

public class Point {
	@Override
	public String toString() {
		return "P(" + x + ", " + y + ")";
	}
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
	
	public final int x;
	public final int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point in(Direction direction) {
		return new Point(direction.x()+x, direction.y()+y);
	}
}