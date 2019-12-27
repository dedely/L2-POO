package td5.chronometer;

public class TestChronometer {

	public static void main(String[] args) {
		Chromometer chromometer = new Chromometer();
		System.out.println(chromometer.toString());

		for (int i = 1; i <= 5000; i++) {
			chromometer.increment();
		}
		System.out.println(chromometer.toString());
		
		for (int i = 1; i <= 3000; i++) {
			chromometer.decrement();
		}
		System.out.println(chromometer.toString());

	}

}
