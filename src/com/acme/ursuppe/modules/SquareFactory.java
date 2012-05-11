package com.acme.ursuppe.modules;

import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;

public interface SquareFactory {
	ISquare make(IBoard board);
}
