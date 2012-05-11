package com.acme.ursuppe.genes;

import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPlayer;

public class IntelligenceGene implements IGeneCard {

	@Override
	public void apply(IPlayer player) {
	}

	@Override
	public void remove(IPlayer player) {
	}

	@Override
	public int mutationPoints() {
		return 3;
	}
	
	@Override
	public int cost() {
		return 1;
	}

	 @Override
	 public String description() {
		 return "Intelligence is useless";
	 }
}
