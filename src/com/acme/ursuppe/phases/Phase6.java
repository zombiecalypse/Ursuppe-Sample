package com.acme.ursuppe.phases;

import java.util.Map;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;
import static com.acme.ursuppe.helpers.Literals.*;

public class Phase6 implements IPhase {
	private final static Map<Integer, Integer> amoebaToScore = map(0,0)
			.and(1, 0)
			.and(2, 0)
			.and(3, 1)
			.and(4, 2)
			.and(5, 4)
			.and(6, 5)
			.and(7, 6).done();
	private final static Map<Integer, Integer> geneToScore = map(0,0)
			.and(1, 0)
			.and(2, 0)
			.and(3, 1)
			.and(4, 2)
			.and(5, 3).done();

	@Override
	public void runPhase(Game game) {
		for (IPlayer player : game.descPlayers()) {
			Integer amoebaScore = amoebaToScore.get(player.countAmoeba());
			Integer geneScore = geneToScore.get(player.countGeneCards());
			
			if (geneScore == null) geneScore = 4;
			
			game.gainScore(player, amoebaScore + geneScore);
		}
	}

}
