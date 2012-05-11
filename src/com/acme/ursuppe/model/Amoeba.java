package com.acme.ursuppe.model;

import java.util.List;
import java.util.Map;

import com.acme.ursuppe.helpers.CollectionHelpers;
import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.ISquare;
import com.google.inject.Inject;

public class Amoeba implements IAmoeba {

	@Override
	public String toString() {
		return "Amoeba [dp=" + damagePoints + ", square=" + square
				+ ", player=" + player + ", damageThreshold=" + damageThreshold
				+ "]";
	}

	private static final int POOP_AMOUNT = 2;
	private Integer damagePoints;
	private ISquare square;
	private final IPlayer player;
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
		assert this.square == null || (this.square.getNeighbors().contains(square)) || this.square.equals(square);
		if (this.square != null) {
			this.square.leave(this);
		}
		this.square = square;
		this.square.enter(this);
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
		List<Integer> otherFoods = CollectionHelpers.getAll(foodstuff, getColor().otherColors());
		for (Integer foodCount : otherFoods) {
			if (foodCount < 1)
				return false;
		}
		for (Integer foodCount : otherFoods) {
			if (foodCount > 1)
				return true;
		}
		return false;
	}

	@Override
	public Color getColor() {
		return player.getColor();
	}

	@Override
	public boolean dies() {
		return this.damagePoints >= this.damageThreshold;
	}

	@Override
	public ISquare getSquare() {
		return this.square;
	}

	@Override
	public void move(Direction moveDirection) {
		ISquare newSquare = this.square.getInDirection(moveDirection);
		moveTo(newSquare);
	}

	@Override
	public Integer getNumber() {
		return this.player.searchAmoeba(this);
	}
	
	@Override
	public void setBack() {
		this.square.leave(this);
		this.damagePoints = 0;
		this.square = null;
	}

}
