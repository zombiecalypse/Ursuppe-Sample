package com.acme.ursuppe.model;

import java.util.List;
import java.util.Map;

import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.ISquare;
import com.google.inject.Inject;

import static com.acme.ursuppe.helpers.MapHelpers.*;

public class Amoeba implements IAmoeba {

	private static final int POOP_AMOUNT = 2;
	private Integer damagePoints;
	private ISquare square;
	private IPlayer player;
	private Integer damageThreshold;

	@Inject
	public Amoeba(IPlayer player) {
		super();
		this.player = player;
		this.damagePoints = 0;
		this.damageThreshold = 2;
	}

	@Override
	public Integer getDamagePoints() {
		return this.damagePoints;
	}

	@Override
	public void moveTo(ISquare square) {
		assert this.square == null || (this.square.getNeighbors().contains(square));
		this.square = square;
	}

	@Override
	public void eat() {
		if (!hasEnoughFood(this.square.getFoodstuff())) {
			takeDamage(1);
			return;
		}
		
		// AK eat from every color once
		for (Color c : getColor().otherColors()) {
			this.square.removeFoodStuff(c);
		}
		
		square.removeFoodStuff(player.whatToEat(square.getFoodstuff()));
		for (int i = 0; i < POOP_AMOUNT ; i++)
			square.addFoodStuff(getColor());	
	}
	
	private void takeDamage(int i) {
		this.damagePoints += i;
	}

	private boolean hasEnoughFood(Map<Color, Integer> foodstuff) {
		List<Integer> otherFoods = getAll(foodstuff, getColor().otherColors());
		for (Integer foodCount : otherFoods) {
			if (foodCount < 1)
				return false;
		}
		for (Integer foodCount : otherFoods) {
			if (foodCount > 1)
				return true;
		}
		assert false;
		return false; // AK to make Eclipse shut up
	}

	@Override
	public Color getColor() {
		return player.getColor();
	}

	@Override
	public boolean dies() {
		return this.damagePoints >= this.damageThreshold;
	}

}