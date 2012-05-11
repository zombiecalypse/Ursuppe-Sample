package com.acme.ursuppe.events;

import com.acme.ursuppe.model.Direction;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPlayer;

public class PlayerMovedEvent extends UrsuppeEvent {

	public PlayerMovedEvent(IPlayer player, IAmoeba amoeba, Direction direction) {
		super();
		this.player = player;
		this.amoeba = amoeba;
		this.direction = direction;
	}

	private final IPlayer player;
	private final IAmoeba amoeba;
	private final Direction direction;

	public IPlayer getPlayer() {
		return this.player;
	}

	public IAmoeba getAmoeba() {
		return this.amoeba;
	}

	public Direction getDirection() {
		return this.direction;
	}
}
