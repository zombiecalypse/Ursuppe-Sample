package com.acme.ursuppe.modules;

import java.util.HashSet;
import java.util.Set;

import com.acme.ursuppe.model.Board;
import com.acme.ursuppe.model.ComputerPlayerFactory;
import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.model.StandardAmoebaFactory;
import com.acme.ursuppe.types.AmoebaFactory;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.PlayerFactory;
import com.acme.ursuppe.types.Point;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class RunnerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PlayerFactory.class).to(ComputerPlayerFactory.class);
		bind(AmoebaFactory.class).to(StandardAmoebaFactory.class);
		bind(SquareFactory.class).to(StandardSquareFactory.class);
		bind(IBoard.class).to(Board.class);
		bind(Game.class).asEagerSingleton();
		install(new StandardPlayers());
		install(new FullPhases());
		install(new StandardGeneCards());
		install(new EnvironmentCards());
	}
	
	@Provides
	Set<Point> squarePoints() {
		Set<Point> points = new HashSet<Point>(); 
		points.add(at(1,0));;
		for (int y = 1; y < 4; y++) {
			for (int x = 0; x < 5; x++) {
				if (x == y && x == 2) continue; // AK the compass
				
				points.add(at(x,y));
			}
		}
		for (int x = 0; x < 4; x++) {
			points.add(at(x,4));
		}
		return points;
	}

	private Point at(int x, int y) {
		return new Point(x,y);
	}

}
