package com.acme.ursuppe.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import com.acme.ursuppe.events.EndOfRoundEvent;
import com.acme.ursuppe.events.InitialPlacement;
import com.acme.ursuppe.events.TypedObservable;
import com.acme.ursuppe.events.UrsuppeEvent;
import com.acme.ursuppe.events.WinnerAnnouncment;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.ISquare;
import com.google.inject.Inject;

/**
 * Organizes the phases and the players. Knows when the game is over and can play it until it is so.
 * Is observable for UIs.
 */
public class Game extends TypedObservable<UrsuppeEvent> {
	private static final int AMOEBAS_AT_START = 2;
	private final List<IPhase> phases;
	private final ScoreBoard scoreboard;
	private final Stack<EnvironmentCard> environmentCards;
	private final Collection<IGeneCard> availableGenes;
	private final IBoard squares;
	
	@Inject
	public Game(Collection<IPlayer> players, 
			List<IPhase> phases, 
			ScoreBoard scoreboard, 
			Stack<EnvironmentCard> environmentCards, 
			Collection<IGeneCard> genes, 
			IBoard board) {
		this.phases = phases;
		this.scoreboard = scoreboard;
		this.environmentCards = environmentCards;
		this.availableGenes = genes;
		this.squares = board;
		for (IPlayer player : players) {
			player.enter(this);
			this.scoreboard.add(player);
		}
	}
	
	public void playRound() {
		for (IPhase phase : phases) {
			phase.runPhase(this);
		}
		this.notifyObservers(new EndOfRoundEvent(this));
	}
	
	public void pushEvent(UrsuppeEvent e) {
		this.notifyObservers(e);
	}
	
	public void play() {
		setup();
		while (!isOver()) {
			playRound();
		}
		this.notifyObservers(new WinnerAnnouncment(this.scoreboard.getBest()));
	}
	
	private void setup() {
		for (IPlayer player : this.ascPlayers()) {
			Collection<ISquare> possible = this.squares.all();
			for (int n = 0; n < AMOEBAS_AT_START; n++) {
				IAmoeba amoeba = player.placeInitial(possible);
				possible.remove(amoeba.getSquare());
				notifyObservers(new InitialPlacement(player, amoeba));
			}
		}
	}

	public boolean isOver() {
		return this.scoreboard.getWinner() != null || this.environmentCards.size() == 1;
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

	public IBoard getBoard() {
		return squares;
	}
}
