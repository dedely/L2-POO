package td5.chronometer;

public class CyclicCounter extends BoundedCounter implements Counting{

	public CyclicCounter(int value, int max) {
		super(value, max);
	}

	@Override
	public void increment() {
		if (getValue() < getMax()) {
			super.increment();
		} else {
			setValue(0);
		}
	}

	@Override
	public void decrement() {
		if (getValue() > 0) {
			super.decrement();
		} else {
			setValue(getMax());
		}
	}

	@Override
	public String toString() {
		return "CyclicCounter [value=" + getValue() + "]";
	}
	
	

}
