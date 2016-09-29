package test.java.innerclass;

public abstract class TrafficLamp {
	public enum Traffic {
		RED(30) {
			public Traffic nextLamp() {
				return GREEN;
			}
		},
		GREEN(45) {
			public Traffic nextLamp() {
				return YELLOW;
			}
		},
		YELLOW(5) {
			public Traffic nextLamp() {
				return RED;
			}
		};

		public abstract Traffic nextLamp();

		private int time;

		private Traffic(int time) {
			this.time = time;
		}

	}
}
