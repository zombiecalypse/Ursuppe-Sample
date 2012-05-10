package com.acme.ursuppe.types;

public interface IAmoeba {

	public abstract Integer getDamagePoints();

	public abstract void moveTo(ISquare square);

	public abstract void eat();
	
	public Color getColor();

	public abstract boolean dies();

}
