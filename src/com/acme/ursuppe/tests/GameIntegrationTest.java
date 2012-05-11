package com.acme.ursuppe.tests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import ch.unibe.jexample.Given;
import ch.unibe.jexample.JExample;

import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.modules.RunnerModule;
import com.acme.ursuppe.types.IBoard;
import com.acme.ursuppe.types.ISquare;
import com.google.inject.Guice;
import com.google.inject.Injector;

@RunWith(JExample.class)
public class GameIntegrationTest {
	
	private Game game;
	private IBoard board;

	@Test
	public IBoard setup() {
		Injector injector = Guice.createInjector(new RunnerModule());
		this.game = injector.getInstance(Game.class);
		this.board = game.getBoard();
		return board;
	}

	@Given("setup")
	public void shouldSetUpBoard(IBoard b) {
		for (ISquare square : board.all()) {
			assertThat(square.getBoard(), is(board));
			assertThat(board.from(square).size(), greaterThanOrEqualTo(1));
			assertThat(board.from(square).size(), lessThanOrEqualTo(4));
		}
	}
	
	@Given("setup")
	public void shouldSetupAmoebas() {
		
	}

}
