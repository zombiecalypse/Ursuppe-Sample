package com.acme.ursuppe.tests;

import java.util.Arrays;
import java.util.List;

import javax.inject.Provider;

import com.acme.ursuppe.model.Player;
import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IPlayer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<List<IPlayer>>() {}).toProvider(PlayerListProvider.class);

	}
	
	static class PlayerListProvider implements Provider<List<IPlayer>> {

		@Override
		public List<IPlayer> get() {
			return Arrays.asList(
				(IPlayer) new Player("Amy", Color.RED),
				(IPlayer) new Player("Bob", Color.BLUE),
				(IPlayer) new Player("Claire", Color.YELLOW));
		}
		
	}

}
