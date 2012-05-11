package com.acme.ursuppe.modules;

import java.util.Arrays;

import com.acme.ursuppe.model.Square;
import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;

public class StandardSquareFactory implements SquareFactory {
	@Override
	public ISquare make(IBoard board) {
		ISquare square = new Square(board);
		for (Color c : Arrays.asList(Color.RED, Color.BLUE, Color.YELLOW)) {
			for (int i = 0; i < 2; i++) {
				square.addFoodStuff(c);
			}
		}
		return square;
	}

}
