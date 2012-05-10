package com.acme.ursuppe.model;

import java.util.Map;

import com.acme.ursuppe.helpers.Literals.MapBuilder;
import com.acme.ursuppe.model.Board.Point;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;
import com.google.inject.Provider;

import static com.acme.ursuppe.helpers.Literals.*;

public class BoardBuilder implements Provider<IBoard> {
	@Override
	public IBoard get() {
		return new Board(squareMap());
	}
	
	private Point at(int x, int y) {
		return new Point(x,y);
	}
	
	private ISquare aSquare() {
		return squareProvider.get();
	}
	
	Provider<Square> squareProvider;

	private Map<Point, ISquare> squareMap() {
		MapBuilder<Point, ISquare> mapBuilder = map(at(1,0), aSquare());
		for (int y = 1; y < 4; y++) {
			for (int x = 0; x < 5; x++) {
				mapBuilder.and(at(x,y), aSquare());
			}
		}
		for (int x = 0; x < 4; x++) {
			mapBuilder.and(at(x,4), aSquare());
		}
		mapBuilder.and(at(2,2), theCompass());
		return mapBuilder.done();
	}

	private ISquare theCompass() {
		return new NullSquare();
	}

}
