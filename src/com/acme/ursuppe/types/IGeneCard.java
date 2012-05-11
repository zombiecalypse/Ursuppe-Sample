package com.acme.ursuppe.types;

public interface IGeneCard {
	void apply(IPlayer player);
	void remove(IPlayer player);
	int mutationPoints();
	int cost();
	String description();
}
