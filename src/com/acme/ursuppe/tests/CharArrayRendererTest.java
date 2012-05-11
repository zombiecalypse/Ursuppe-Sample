package com.acme.ursuppe.tests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.acme.ursuppe.renderer.CharArrayRenderer;

public class CharArrayRendererTest {
	CharArrayRenderer renderer = new CharArrayRenderer(10,5);

	@Test
	public void shouldDrawSingleChar() {
		renderer.draw(1, 0, "x");
		assertThat(renderer.toString(), is(
				" x        \n" +
				"          \n" +
				"          \n" +
				"          \n" +
				"          \n"));
	}
	
	@Test
	public void shouldDrawLine() {
		renderer.draw(1, 0, "xyz");
		assertThat(renderer.toString(), is(
				" xyz      \n" +
				"          \n" +
				"          \n" +
				"          \n" +
				"          \n"));
	}

	@Test
	public void shouldDrawBox() {
		renderer.draw(1, 0, "xy\nzw");
		assertThat(renderer.toString(), is(
				" xy       \n" +
				" zw       \n" +
				"          \n" +
				"          \n" +
				"          \n"));
	}
}
