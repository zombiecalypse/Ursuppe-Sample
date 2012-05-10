package com.acme.ursuppe.tests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import ch.unibe.jexample.*;

import com.acme.ursuppe.model.Amoeba;
import static com.acme.ursuppe.helpers.Literals.*;
import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPlayer;
import com.acme.ursuppe.types.ISquare;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

@RunWith(JExample.class)
public class AmoebaTest {
	class AmoebaTestModule extends AbstractModule {
		protected void configure() {
			bind(IAmoeba.class).to(Amoeba.class);
			bind(ISquare.class).toInstance(mock(ISquare.class));
			bind(IPlayer.class).toInstance(mock(IPlayer.class));
		}
	}

	private IAmoeba amoeba;
	private Injector injector;
	private ISquare square;
	private IPlayer player;

	@Test
	public IAmoeba freshOutOfBox() {
		this.injector = Guice.createInjector(new AmoebaTestModule());
		this.amoeba = injector.getInstance(Amoeba.class);
		// AK Get the mocks:
		this.square = injector.getInstance(ISquare.class); 
		this.player = injector.getInstance(IPlayer.class);
		assertThat(amoeba.getDamagePoints(), is(0));
		return amoeba;
	}
	
	private Map<Color, Integer> foodmap(int red, int blue, int yellow) {
		return map(Color.RED, red).and(Color.BLUE, blue).and(Color.YELLOW, yellow).done();
	}
	
	@Given("freshOutOfBox")
	public IAmoeba takesDamageWhenNoFoodAvailable(IAmoeba amoeba) {
		when(square.getFoodstuff()).thenReturn(foodmap(0,0,0));
		when(player.getColor()).thenReturn(Color.RED);
		amoeba.moveTo(square);
		amoeba.eat();
		assertThat(amoeba.getDamagePoints(), is(1));
		return amoeba;
	}
	
	@Given("freshOutOfBox")
	public IAmoeba eatsWhenFoodAvailable(IAmoeba amoeba) {
		when(square.getFoodstuff()).thenReturn(foodmap(0,2,1));
		when(player.getColor()).thenReturn(Color.RED);
		when(player.whatToEat((Map<Color, Integer>) anyObject())).thenReturn(Color.BLUE);
		amoeba.moveTo(square);
		amoeba.eat();
		verify(square, times(2)).removeFoodStuff(Color.BLUE);
		verify(square).removeFoodStuff(Color.YELLOW);
		verify(square, times(2)).addFoodStuff(Color.RED);
		assertThat(amoeba.getDamagePoints(), is(0));
		return amoeba;
	}
	
	@Given("takesDamageWhenNoFoodAvailable")
	public IAmoeba diesOnTooMuchDamage(IAmoeba amoeba) {
		amoeba.eat();
		assertThat(amoeba.getDamagePoints(), is(2));
		assertTrue(amoeba.dies());
		return amoeba;
	}

}
