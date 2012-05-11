package com.acme.ursuppe.modules;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

import javax.inject.Provider;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import com.acme.ursuppe.model.*;

public class EnvironmentCards extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Stack<EnvironmentCard>>(){}).toProvider(EnvironmentCardProvider.class);
	}

	static class EnvironmentCardProvider implements Provider<Stack<EnvironmentCard>> {

		@Override
		public Stack<EnvironmentCard> get() {
			LinkedList<EnvironmentCard> list = new LinkedList<EnvironmentCard>(Arrays.asList(
					new EnvironmentCard(Direction.NORTH, 10),
					new EnvironmentCard(Direction.WEST, 14),
					new EnvironmentCard(Direction.EAST, 9),
					new EnvironmentCard(Direction.SOUTH, 10),
					new EnvironmentCard(Direction.WEST, 12),
					new EnvironmentCard(Direction.EAST, 13),
					new EnvironmentCard(Direction.NORTH, 16),
					new EnvironmentCard(Direction.WEST, 8),
					new EnvironmentCard(Direction.EAST, 12),
					new EnvironmentCard(Direction.SOUTH, 10),
					new EnvironmentCard(Direction.WEST, 12),
					new EnvironmentCard(Direction.EAST, 13),
					new EnvironmentCard(Direction.SOUTH, 12),
					new EnvironmentCard(Direction.SOUTH, 9)
					));
			Collections.shuffle(list);
			Stack<EnvironmentCard> stack = new Stack<EnvironmentCard>();
			stack.addAll(list);
			return stack;
		}
		
	}
}
