package com.acme.ursuppe.types;

import java.util.Arrays;
import java.util.Collection;

public enum Color {
	RED {
		public Collection<Color> otherColors() {
			return Arrays.asList(YELLOW, BLUE);
		}
	}, YELLOW {
		public Collection<Color> otherColors() {
			return Arrays.asList(RED, BLUE);
		}
	}, BLUE {
		public Collection<Color> otherColors() {
			return Arrays.asList(RED, YELLOW);
		}
	};

	public abstract Collection<Color> otherColors();
}
