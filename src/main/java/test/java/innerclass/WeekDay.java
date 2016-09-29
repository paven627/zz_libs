package test.java.innerclass;

public abstract class WeekDay {
	private int day = 0;

	public abstract WeekDay nextDay();

	private WeekDay() {
	}

	private WeekDay(int day) {
		this.day = day;
	}

	public static final WeekDay MONDAY = new WeekDay(1) {
		@Override
		public WeekDay nextDay() {
			return TUESDAY;
		}
	};

	public static final WeekDay TUESDAY = new WeekDay(2) {
		@Override
		public WeekDay nextDay() {
			return WEDNESDAY;
		}
	};

	public static final WeekDay WEDNESDAY = new WeekDay(3) {
		@Override
		public WeekDay nextDay() {
			return MONDAY;
		}

	};

}
