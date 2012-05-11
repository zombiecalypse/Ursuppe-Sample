package com.acme.ursuppe.modules;

import java.util.Collection;
import java.util.LinkedList;

import com.acme.ursuppe.types.IGeneCard;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class StandardGeneCards extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Collection<IGeneCard>>(){}).toInstance(new LinkedList<IGeneCard>()); // TODO

	}

}
