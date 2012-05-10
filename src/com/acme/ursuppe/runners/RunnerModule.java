package com.acme.ursuppe.runners;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.model.Player;
import com.acme.ursuppe.phases.Phase1;
import com.acme.ursuppe.phases.Phase2;
import com.acme.ursuppe.phases.Phase3;
import com.acme.ursuppe.phases.Phase4;
import com.acme.ursuppe.phases.Phase5;
import com.acme.ursuppe.phases.Phase6;
import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IPhase;
import com.acme.ursuppe.types.IPlayer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class RunnerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<List<IPhase>>(){}).toInstance(Arrays.asList(
				(IPhase) new Phase1(), 
				new Phase2(), 
				new Phase3(), 
				new Phase4(), 
				new Phase5(), 
				new Phase6()));
		bind(new TypeLiteral<Collection<IPlayer>>(){}).toInstance(Arrays.asList(
				(IPlayer) new Player("Alice", Color.RED),
				new Player("Bob", Color.BLUE),
				new Player("Claire", Color.YELLOW)));
		bind(Game.class).asEagerSingleton();

	}

}
