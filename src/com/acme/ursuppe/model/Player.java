package com.acme.ursuppe.model;

import java.util.Map;

import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IPlayer;

public class Player implements IPlayer {

	@Override
	public String toString() {
		return "P[name=" + name + ", color=" + color + "]";
	}

	private String name;
	private Color color;

	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color whatToEat(Map<Color, Integer> foodstuff) {
		// TODO Auto-generated method stub
		return null;
	}

}
