package com.acme.ursuppe.modules;

import java.util.Arrays;
import java.util.List;

import com.acme.ursuppe.phases.Phase1;
import com.acme.ursuppe.phases.Phase2;
import com.acme.ursuppe.phases.Phase3;
import com.acme.ursuppe.phases.Phase4;
import com.acme.ursuppe.phases.Phase5;
import com.acme.ursuppe.phases.Phase6;
import com.acme.ursuppe.types.IPhase;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class FullPhases extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<List<IPhase>>(){}).toInstance(Arrays.asList(
				new Phase1(), 
				new Phase2(), 
				new Phase3(), 
				new Phase4(), 
				new Phase5(), 
				new Phase6()));
	}

}
