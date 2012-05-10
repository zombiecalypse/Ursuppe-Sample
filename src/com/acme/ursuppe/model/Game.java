package com.acme.ursuppe.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.Stack;

import com.acme.ursuppe.events.TypedObservable;
import com.acme.ursuppe.events.UrsuppeEvent;
import com.acme.ursuppe.types.IBoard;
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
	private Stack<EnvironmentCard> environmentCards;
	private Set<IGeneCard> availableGenes;
	private IBoard squares;
	
	@Inject
	public Game(Collection<IPlayer> players, List<IPhase> phases, ScoreBoard scoreboard, Stack<EnvironmentCard> environmentCards, Set<IGeneCard> genes, IBoard board) {
		this.phases = phases;
		this.scoreboard = scoreboard;
		this.environmentCards = environmentCards;
		this.availableGenes = genes;
		this.squares = board;
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
		environmentCards.pop();
		return getOzoneScore();
	}
	
	public int getOzoneScore() {
		return environmentCards.peek().ozoneScore;
	}
	
	public Direction getDirection() {
		return environmentCards.peek().direction;
	}

	public void addGenes(Collection<IGeneCard> genes) {
		availableGenes.addAll(genes);
	}

	public Collection<IGeneCard> availableGenes() {
		return new HashSet<IGeneCard>(availableGenes);
	}

	public void removeGenes(Collection<IGeneCard> boughtGenes) {
		availableGenes.removeAll(boughtGenes);
	}

	public void gainScore(IPlayer player, int i) {
		scoreboard.addScore(player, i);
	}
}
