package com.acme.ursuppe.phases;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;

public class Phase1 implements IPhase {

	@Override
	public void runPhase(Game game) {
		for (IPlayer player : game.ascPlayers()) {
			player.moveAndFeedAmoebas(game.getDirection());
		}
	}
}
