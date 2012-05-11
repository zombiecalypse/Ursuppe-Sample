package com.acme.ursuppe.model;

import com.acme.ursuppe.types.AmoebaFactory;
import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.PlayerFactory;
import com.google.inject.Inject;

public class ComputerPlayerFactory implements PlayerFactory {

	private final AmoebaFactory amoebaFactory;
	
	@Inject
	public ComputerPlayerFactory(AmoebaFactory af) {
		this.amoebaFactory = af;
	}
	
	@Override
	public IPlayer make(String string, Color color) {
		return new Player(string, color, amoebaFactory);
	}

}
