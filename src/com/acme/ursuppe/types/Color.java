package com.acme.ursuppe.types;

import java.util.Arrays;
import java.util.Collection;

public enum Color {
	RED {
		@Override
		public Collection<Color> otherColors() {
			return Arrays.asList(YELLOW, BLUE);
		}

		@Override
		public String getName() {
			return "red";
		}
	}, YELLOW {
		@Override
		public Collection<Color> otherColors() {
			return Arrays.asList(RED, BLUE);
		}

		@Override
		public String getName() {
			return "yellow";
		}
	}, BLUE {
		@Override
		public Collection<Color> otherColors() {
			return Arrays.asList(RED, YELLOW);
		}

		@Override
		public String getName() {
			return "blue";
		}
	};

	public abstract Collection<Color> otherColors();
	public abstract String getName();
	
	@Override
	public String toString() {
		return getName();
	}

	public char getChar() {
		return getName().charAt(0);
	}
}
