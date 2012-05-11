package com.acme.ursuppe.modules;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Provider;

import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.PlayerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.TypeLiteral;

public class StandardPlayers extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Collection<IPlayer>>() {}).toProvider(PlayerListProvider.class);
	}
	
	static class PlayerListProvider implements Provider<List<IPlayer>> {
		
		private final PlayerFactory playerFactory;
		
		@Inject
		public PlayerListProvider(PlayerFactory pf) {
			this.playerFactory= pf;
		}

		@Override
		public List<IPlayer> get() {
			return Arrays.asList(
				playerFactory.make("Amy", Color.RED),
				playerFactory.make("Bob", Color.BLUE),
				playerFactory.make("Claire", Color.YELLOW));
		}
		
	}
}
