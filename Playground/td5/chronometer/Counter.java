package td5.chronometer;

public class Counter implements Counting{
	/**
	 * The current value of the counter should always be positive.
	 */
	private int value;

	public Counter(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Counter [value=" + value + "]";
	}


	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}

	public void increment() {
		value++;
	}

	public void decrement() {
		if (value > 0) {
			value--;
		}
	}

	public void init() {
		value = 0;
	}

}
