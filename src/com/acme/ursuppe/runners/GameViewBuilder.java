package com.acme.ursuppe.runners;

import java.util.Map.Entry;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.renderer.CharArrayRenderer;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;
import com.acme.ursuppe.types.Point;

public class GameViewBuilder extends CharArrayRenderer {

	public GameViewBuilder() {
		super(80,30);
	}
	
	public String show(Game g) {
		final IBoard board = g.getBoard();
		for (Entry<Point, ISquare> place : board) {
			int x = place.getKey().x;
			int y = place.getKey().y;
			render(x, y, place.getValue());
			
		}
		return this.toString();
	}

	private void render(int x, int y, ISquare square) {
		SquareView view = new SquareView(square);
		this.draw(x*view.WIDTH, y*view.HEIGHT, view.show());
	}
}
