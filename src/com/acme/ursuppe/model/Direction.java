package com.acme.ursuppe.model;

public enum Direction {
	NORTH {
		@Override
		public Integer x() {
			return 0;
		}

		@Override
		public Integer y() {
			return 1;
		}
	}, EAST {
		@Override
		public Integer x() {
			return 1;
		}

		@Override
		public Integer y() {
			return 0;
		}
	}, SOUTH {
		@Override
		public Integer x() {
			return 0;
		}

		@Override
		public Integer y() {
			return -1;
		}
	}, WEST {
		@Override
		public Integer x() {
			return -1;
		}

		@Override
		public Integer y() {
			return 0;
		}
	}, STAY {
		@Override
		public Integer x() {
			return 0;
		}

		@Override
		public Integer y() {
			return 0;
		}
	}, CHOOSE {
		@Override
		public Integer x() {
			return null;
		}

		@Override
		public Integer y() {
			return null;
		}
	};

	public abstract Integer x(); 
	public abstract Integer y(); 
}
