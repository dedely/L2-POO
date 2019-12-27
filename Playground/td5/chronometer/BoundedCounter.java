package td5.chronometer;

public class BoundedCounter extends Counter implements Counting {
	private int max;

	public BoundedCounter(int value, int max) {
		super(value);
		this.max = max;
	}

	@Override
	public void increment() {
		if (getValue() < max) {
			super.increment();
		}
	}

	@Override
	public String toString() {
		return "BoundedCounter [value=" + getValue() + "]";
	}

	protected int getMax() {
		return max;
	}
}
