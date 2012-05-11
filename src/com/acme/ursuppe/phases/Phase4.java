package com.acme.ursuppe.phases;

import java.util.Collection;

import com.acme.ursuppe.events.BuyAmoebaAnnouncement;
import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;

public class Phase4 implements IPhase {

	private static final int biopoints_per_round = 10;

	@Override
	public void runPhase(Game game) {
		for (IPlayer player : game.descPlayers()) {
			player.receiveBiopoints(biopoints_per_round);
			
			while (player.willBuyAmoeba()) {
				Collection<IAmoeba> amoebas = player.buyAmoeba();
				for (IAmoeba a : amoebas) {
					game.pushEvent(new BuyAmoebaAnnouncement(player, a));
				}
			}
		}
	}

}
