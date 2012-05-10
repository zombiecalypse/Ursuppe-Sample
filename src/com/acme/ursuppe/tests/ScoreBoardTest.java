package com.acme.ursuppe.tests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import javax.inject.Provider;

import ch.unibe.jexample.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acme.ursuppe.model.ScoreBoard;
import com.acme.ursuppe.types.IPlayer;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import static com.acme.ursuppe.helpers.CollectionHelpers.*;

@RunWith(JExample.class)
public class ScoreBoardTest {

	private Injector injector;
	private ScoreBoard scoreboard;
	
	private List<IPlayer> players;
	private IPlayer alice;
	private IPlayer bob;
	private IPlayer claire;
	
	

	@Test
	public void buildEmpty() {
		this.injector = Guice.createInjector(new TestModule());
		this.scoreboard = injector.getInstance(ScoreBoard.class);
		this.players = injector.getInstance(Key.get(new TypeLiteral<List<IPlayer>>(){}));
		this.alice = players.get(0);
		this.bob = players.get(1);
		this.claire = players.get(2);
	}
	
	@Test
	public void addPlayers() {
		buildEmpty();
		scoreboard.add(alice);
		scoreboard.add(bob);
		scoreboard.add(claire);
		assertThat(scoreboard.ascOrder(), is(players));
		assertThat(scoreboard.descOrder(), is(reversed(players)));
	}
	
	@Test
	public void shouldJumpOverOccupied() {
		addPlayers();
		scoreboard.addScore(alice, 1);
		assertThat(scoreboard.ascOrder(), is(Arrays.asList(bob, claire, alice)));
	}
	
	@Test
	public void shouldJumpOverButNotIfPlaceIsActuallyFree() {
		addPlayers();
		scoreboard.addScore(bob, 1);
		scoreboard.addScore(alice, 1);
		assertThat(scoreboard.ascOrder(), is(Arrays.asList(alice, claire, bob)));
	}

}
