package com.acme.ursuppe.events;

import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPlayer;

public class BuyAmoebaAnnouncement extends UrsuppeEvent {

	public BuyAmoebaAnnouncement(IPlayer player, IAmoeba amoeba) {
		super();
		this.player = player;
		this.amoeba = amoeba;
	}

	private final IPlayer player;
	private final IAmoeba amoeba;

	public IPlayer getPlayer() {
		return this.player;
	}

	public IAmoeba getAmoeba() {
		return this.amoeba;
	}

}
