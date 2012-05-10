package com.acme.ursuppe.types;

import java.util.Map;

import com.acme.ursuppe.model.Direction;

public interface IBoard {

	Map<Direction, ISquare> from(ISquare square);

	ISquare getNullSquare();

}
