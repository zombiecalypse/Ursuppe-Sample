package com.acme.ursuppe.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acme.ursuppe.types.IPlayer;
import static com.acme.ursuppe.helpers.CollectionHelpers.*;

public class ScoreBoard {
	private static final Integer WINNING_SCORE = 36;
	
	private Map<IPlayer, Integer> scores = new HashMap<IPlayer, Integer>();
	
	private final Comparator<IPlayer> asc = new Comparator<IPlayer>() {
		@Override
		public int compare(IPlayer arg0, IPlayer arg1) {
			return scores.get(arg0).compareTo(scores.get(arg1));
		}
	};
	private final Comparator<IPlayer> desc = Collections.reverseOrder(asc);
	
	private Integer max() {
		if (scores.isEmpty()) return 1;
		
		return Collections.max(scores.values());
	}
	/**
	 * can only be called before the game.
	 */
	public void add(IPlayer player) {
		scores.put(player, max()+1);
	}

	public List<IPlayer> ascOrder() {
		return sorted(list(scores.keySet()), asc);
	}
	
	public List<IPlayer> descOrder() {
		return sorted(list(scores.keySet()), desc);
	}
	public void addScore(IPlayer iPlayer, int i) {
		assert scores.get(iPlayer) != null;
		int from = scores.get(iPlayer);
		
		while (scores.containsValue(from+i)) i++;
		
		scores.put(iPlayer, from+i);
		
	}
	public IPlayer getWinner() {
		if (max() < WINNING_SCORE) return null;
		return getBest();
	}
	
	public IPlayer getBest() {
		return Collections.max(scores.keySet(), asc);
	}
}
