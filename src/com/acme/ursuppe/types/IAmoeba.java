package com.acme.ursuppe.types;

import com.acme.ursuppe.model.Direction;

public interface IAmoeba {

	public abstract Integer getDamagePoints();

	public abstract void moveTo(ISquare square);

	public abstract void eat();
	
	public Color getColor();

	public abstract boolean dies();

	public abstract ISquare getSquare();

	public abstract void move(Direction driftDirection);

	public abstract Integer getNumber();

	void setBack();

}
