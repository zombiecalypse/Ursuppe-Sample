package com.acme.ursuppe.phases;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;

public class Phase5 implements IPhase {

	@Override
	public void runPhase(Game game) {
		for (IPlayer player : game.players()) {
			player.killOffAmoebas();
		}
	}

}
