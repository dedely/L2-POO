package td5.geometry;

/**
 * We don't need to implement the two calculations again because it works in the
 * same way for a square.
 *
 */
public class Square extends Rectangle {

	public Square(Point point, int distance) {
		super(point, distance, distance);
	}

}
