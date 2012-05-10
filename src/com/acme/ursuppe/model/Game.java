package com.acme.ursuppe.model;

import java.util.Collection;
import java.util.List;
import java.util.Observable;

import com.acme.ursuppe.events.TypedObservable;
import com.acme.ursuppe.events.UrsuppeEvent;
import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;
import com.google.inject.Inject;

/**
 * Organizes the phases and the players. Knows when the game is over and can play it until it is so.
 * Is observable for UIs.
 */
public class Game extends TypedObservable<UrsuppeEvent> {
	private List<IPhase> phases;
	private ScoreBoard scoreboard;
	
	@Inject
	public Game(Collection<IPlayer> players, List<IPhase> phases, ScoreBoard scoreboard) {
		this.phases = phases;
		this.scoreboard = scoreboard;
		for (IPlayer player : players) {
			this.scoreboard.add(player);
		}
	}
	
	public void playRound() {
		for (IPhase phase : phases) {
			phase.runPhase(this);
		}
	}
	
	public void play() {
		while (!isOver()) {
			playRound();
		}
	}
	
	public boolean isOver() {
		return this.scoreboard.getWinner() != null;
	}
	
	public Collection<IPlayer> players() {
		return this.scoreboard.ascOrder();
	}

	public List<IPlayer> ascPlayers() {
		return this.scoreboard.ascOrder();
	}
	
	public List<IPlayer> descPlayers() {
		return this.scoreboard.descOrder();
	}
	
	public int changeEnvironmentCard() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addGenes(Collection<IGeneCard> genes) {
		// TODO Auto-generated method stub
		
	}

	public Collection<IGeneCard> availableGenes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeGenes(Collection<IGeneCard> boughtGenes) {
		// TODO Auto-generated method stub
		
	}

	public void gainScore(IPlayer player, int i) {
		// TODO Auto-generated method stub
		
	}

	
}
