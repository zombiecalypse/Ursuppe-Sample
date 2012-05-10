package com.acme.ursuppe.phases;

import java.util.Collection;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;

public class Phase3 implements IPhase {

	@Override
	public void runPhase(Game game) {
		for (IPlayer player : game.descPlayers()) {
			Collection<IGeneCard> boughtGenes = player.buyGenes(game.availableGenes());
			
			game.removeGenes(boughtGenes);
		}
	}

}
