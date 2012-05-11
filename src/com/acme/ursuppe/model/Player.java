package com.acme.ursuppe.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.acme.ursuppe.types.AmoebaFactory;
import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IGeneCard;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.ISquare;
import static com.acme.ursuppe.helpers.CollectionHelpers.*;

public class Player implements IPlayer {
	private static Comparator<IGeneCard> utilityOfGene = new Comparator<IGeneCard>() {
		@Override
		public int compare(IGeneCard arg0, IGeneCard arg1) {
			return 0; // AK Imagine something smart here.
		}
	};

	private static final int AMOEBA_BP_COST = 6;

	@Override
	public String toString() {
		return "P[name=" + name + ", color=" + color + "]";
	}

	private String name;
	private Color color;
	private Collection<IGeneCard> genes = new HashSet<IGeneCard>();
	private int biopoints = 0;
	private List<IAmoeba> amoebasOnBoard = new ArrayList<IAmoeba>();
	private Set<IAmoeba> amoebasOffBoard = new HashSet<IAmoeba>();

	private int price_for_movement = 1;


	public Player(String name, Color color, AmoebaFactory amoebafactory) {
		this.name = name;
		this.color = color;
		for (int i = 0; i < 7; i++) {
			this.amoebasOffBoard.add(amoebafactory.make(this));
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Color whatToEat(final Map<Color, Integer> foodstuff) {
		Comparator<Color> mostFoodstuff  = new Comparator<Color>() {
			@Override
			public int compare(Color o1, Color o2) {
				return foodstuff.get(o1).compareTo(foodstuff.get(o2));
			}
		};
		return Collections.max(foodstuff.keySet(), mostFoodstuff);
	}

	@Override
	public void moveAndFeedAmoebas(Direction driftDirection) {
		assert amoebasOnBoard.size() > 0;
		for (IAmoeba amoeba : amoebasOnBoard) {
			assert amoeba.getSquare().getBoard().all().contains(amoeba.getSquare());
			if (willMove(amoeba) || driftDirection == Direction.CHOOSE) {
				amoeba.move(moveAmoebaTowards(amoeba));
				biopoints -= price_for_movement ;
			}
			else {
				amoeba.move(driftDirection);
			}
			amoeba.eat();
		}
	}

	/**
	 * Template method that choses the direction to move for an amoeba
	 * @param amoeba
	 * @return
	 */
	private Direction moveAmoebaTowards(IAmoeba amoeba) {
		Collection<ISquare> available = amoeba.getSquare().getNeighbors();
		return amoeba.getSquare().getDirectionOf(choose(available));
	}

	/**
	 * Template method to decide if amoeba should move.
	 * @param amoeba
	 * @return
	 */
	private boolean willMove(IAmoeba amoeba) {
		return false; // AK current AI will let amoeba drift
	}

	@Override
	public int getMutationPoints() {
		return sumMutationPoints(genes);
	}
	
	public int sumMutationPoints(Collection<IGeneCard> genes) {
		int sum = 0;
		for (IGeneCard gene : genes) {
			sum += gene.mutationPoints();
		}
		return sum;
	}

	@Override
	public Collection<IGeneCard> sellGenes(int newOzoneValue) {
		Set<IGeneCard> selling = new HashSet<IGeneCard>();
		while (getMutationPoints() - sumMutationPoints(selling) > newOzoneValue) {
			selling.add(Collections.min(genes, utilityOfGene));
		}
		return selling;
	}

	@Override
	public Collection<IGeneCard> buyGenes(Collection<IGeneCard> availableGenes) {
		return new HashSet<IGeneCard>(); // AK current AI never buys genes.
	}

	/**
	 * Template method for decision to pay instead of removing further genes.
	 */
	@Override
	public boolean willPay(int differenceCost) {
		return differenceCost < biopoints;
	}

	@Override
	public void pay(int differenceCost) {
		assert biopoints >= differenceCost : String.format("Can't pay %d with only %d biopoints", differenceCost, biopoints);
		
		biopoints -= differenceCost;
	}

	@Override
	public void receiveBiopoints(int bp) {
		biopoints += bp;
	}

	@Override
	public boolean willBuyAmoeba() {
		return biopoints > 6 && !availableSquaresForPlacing().isEmpty() && !amoebasOffBoard.isEmpty();
	}

	@Override
	public Collection<IAmoeba> buyAmoeba() {
		assert biopoints >= AMOEBA_BP_COST : String.format("Can't pay %d for amoeba with only %d biopoints", AMOEBA_BP_COST, biopoints);
		List<IAmoeba> added = new LinkedList<IAmoeba>();
		while (willBuyAmoeba()) {
			biopoints -= AMOEBA_BP_COST;
			added.add(placeAmoeba());
		}
		return added;
	}

	@Override
	public IAmoeba placeAmoeba() {
		ISquare place = choosePlace(availableSquaresForPlacing());
		IAmoeba newAmoeba = takeAmoeba();
		newAmoeba.moveTo(place);
		return newAmoeba;
	}

	
	/**
	 * Template method for decision where to place amoeba.
	 */
	protected ISquare choosePlace(Collection<ISquare> availableSquaresForPlacing) {
		return choose(availableSquaresForPlacing);
	}

	private Set<ISquare> availableSquaresForPlacing() {
		Set<ISquare> available = new HashSet<ISquare>();
		for (IAmoeba amoeba : amoebasOnBoard) {
			available.addAll(amoeba.getSquare().getNeighbors());
		}
		return available;
	}

	@Override
	public void killOffAmoebas() {
		for (IAmoeba amoeba : new LinkedList<IAmoeba>(amoebasOnBoard)) {
			if (amoeba.dies()) {
				amoebasOffBoard.add(amoeba);
				amoebasOnBoard.remove(amoeba);
				amoeba.setBack();
				// TODO event for death
			}
		}
	}

	@Override
	public int countAmoeba() {
		return amoebasOnBoard.size();
	}

	@Override
	public int countGeneCards() {
		return genes.size();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Integer searchAmoeba(IAmoeba amoeba) {
		return this.amoebasOnBoard.indexOf(amoeba);
	}
	
	private IAmoeba takeAmoeba() {
		IAmoeba newAmoeba = any(amoebasOffBoard);
		amoebasOffBoard.remove(newAmoeba);
		amoebasOnBoard.add(newAmoeba);
		return newAmoeba;
	}

	@Override
	public IAmoeba placeInitial(Collection<ISquare> all) {
		ISquare place = choosePlace(all);
		IAmoeba newAmoeba = takeAmoeba();
		newAmoeba.moveTo(place);
		return newAmoeba;
	}

}
