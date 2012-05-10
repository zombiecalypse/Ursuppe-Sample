package com.acme.ursuppe.phases;

import java.util.Collection;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;

public class Phase2 implements IPhase {

	@Override
	public void runPhase(Game game) {
		final int newOzoneValue = game.changeEnvironmentCard();
		for (IPlayer player : game.players()) {
			while (player.getMutationPoints() > newOzoneValue) {
				int differenceCost = newOzoneValue - player.getMutationPoints();
				if (player.willPay(differenceCost)) {
					player.pay(differenceCost);
					break;
				}
				
				Collection<IGeneCard> genes = player.sellGenes(newOzoneValue);
				
				game.addGenes(genes);
				
			}
		}
	}

}
