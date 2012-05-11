package com.acme.ursuppe.runners;

import com.acme.ursuppe.modules.RunnerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class TextRunner {
	static class TextRunnerModule extends RunnerModule {
		@Override
		protected void configure() {
			super.configure();
		}
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TextRunnerModule());
		TextUi ui = injector.getInstance(TextUi.class);
		ui.run();
	}

}
