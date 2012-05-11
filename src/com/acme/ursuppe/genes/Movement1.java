package com.acme.ursuppe.genes;

import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPlayer;

public class Movement1 implements IGeneCard {

	@Override
	public void apply(IPlayer player) {
		player.setNumberOfDiceForMovement(2);

	}

	@Override
	public void remove(IPlayer player) {
		player.setNumberOfDiceForMovement(1);

	}

	@Override
	public int mutationPoints() {
		return 2;
	}

	@Override
	public int cost() {
		return 2;
	}

	@Override
	public String description() {
		return "In movement, the player can choose the prefered of two dice.";
	}

}
