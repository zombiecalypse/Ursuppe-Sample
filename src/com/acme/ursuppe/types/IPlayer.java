package com.acme.ursuppe.types;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.acme.ursuppe.model.Direction;
import com.acme.ursuppe.model.Game;

public interface IPlayer {

	Color getColor();

	Color whatToEat(Map<Color, Integer> foodstuff);

	void moveAndFeedAmoebas(Direction driftDirection);

	int getMutationPoints();

	Collection<IGeneCard> sellGenes(int newOzoneValue);

	Collection<IGeneCard> buyGenes(Collection<IGeneCard> availableGenes);

	boolean willPay(int differenceCost);

	void pay(int differenceCost);

	void receiveBiopoints(int biopointsPerRound);

	boolean willBuyAmoeba();

	Collection<IAmoeba> buyAmoeba();

	void killOffAmoebas();

	int countAmoeba();

	int countGeneCards();

	String getName();

	Integer searchAmoeba(IAmoeba amoeba);

	IAmoeba placeAmoeba();

	IAmoeba placeInitial(Collection<ISquare> all);

	void setNumberOfDiceForMovement(int i);

	void setAvailableSquareStrategy(AvailableSquares as);

	AvailableSquares getAvailableSquareStrategy();
	
	public abstract class AvailableSquares {
		public abstract Set<ISquare> get();
	}

	IBoard getBoard();

	void enter(Game game);


}
