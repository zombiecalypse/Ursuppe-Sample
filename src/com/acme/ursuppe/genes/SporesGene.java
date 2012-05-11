package com.acme.ursuppe.genes;

import java.util.HashSet;
import java.util.Set;

import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.IPlayer.AvailableSquares;
import com.acme.ursuppe.types.ISquare;

import static com.acme.ursuppe.helpers.CollectionHelpers.*;

public class SporesGene implements IGeneCard {

	private AvailableSquares oldStrategy;
	
	class AllSquares extends AvailableSquares {
		
		private IPlayer player;
		public AllSquares(IPlayer p) {
			this.player = p;
		}
		@Override
		public Set<ISquare> get() {
			return new HashSet<ISquare>(player.getBoard().all());
		}
	};

	@Override
	public void apply(IPlayer player) {
		this.oldStrategy = player.getAvailableSquareStrategy();
		player.setAvailableSquareStrategy(new AllSquares(player));
	}

	@Override
	public void remove(IPlayer player) {
		player.setAvailableSquareStrategy(oldStrategy);
	}

	@Override
	public int mutationPoints() {
		return 3;
	}

	@Override
	public int cost() {
		return 1;
	}

	@Override
	public String description() {
		return "Place an amoeba anywhere on the board.";
	}

}
