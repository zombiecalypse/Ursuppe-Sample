package com.acme.ursuppe.types;

import java.util.Collection;
import java.util.Map;

public interface IPlayer {

	Color getColor();

	Color whatToEat(Map<Color, Integer> foodstuff);

	void moveAndFeedAmoebas();

	int getMutationPoints();

	Collection<IGeneCard> sellGenes(int newOzoneValue);

	Collection<IGeneCard> buyGenes(Collection<IGeneCard> availableGenes);

	boolean willPay(int differenceCost);

	void pay(int differenceCost);

	void receiveBiopoints(int biopointsPerRound);

	boolean willBuyAmoeba();

	void buyAmoeba();

	void killOffAmoebas();

	int countAmoeba();

	int countGeneCards();

}
