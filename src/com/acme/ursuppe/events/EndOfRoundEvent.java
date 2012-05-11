package com.acme.ursuppe.events;

import com.acme.ursuppe.model.Game;

public class EndOfRoundEvent extends UrsuppeEvent {
	private final Game game;

	public EndOfRoundEvent(Game g) {
		this.game = g;
	}

	public Game getGame() {
		return game;
	}

}
