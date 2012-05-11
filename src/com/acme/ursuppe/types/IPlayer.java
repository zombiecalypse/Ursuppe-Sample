package com.acme.ursuppe.types;

import java.util.Collection;
import java.util.Map;
import com.acme.ursuppe.model.Direction;

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

}
