package td5.chronometer;

public class Chromometer {
	private Counting hour;
	private Counting minute;
	private Counting second;

	public Chromometer() {
		hour = new CyclicCounter(0, 99);
		minute = new CyclicCounter(0, 59);
		second = new CyclicCounter(0, 59);
	}

	public void increment() {
		second.increment();
		if (second.getValue() == 0) {
			minute.increment();
			if (minute.getValue() == 0) {
				hour.increment();
			}
		}
	}

	public void decrement() {
		second.decrement();
		if (second.getValue() == 59) {
			minute.decrement();
			if (minute.getValue() == 59) {
				hour.decrement();
			}
		}
	}

	@Override
	public String toString() {
		return "Chromometer [hour=" + hour.getValue() + ", minute=" + minute.getValue() + ", second=" + second.getValue() + "]";
	}

}
