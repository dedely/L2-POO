package td5.geometry;

public class Rectangle extends GeometricFigure {
	private int height;

	public Rectangle(Point point, int distance, int height) {
		super(point, distance);
		this.height = height;
	}

	@Override
	public double calculateSurface() {
		return getDistance() * height;
	}

	@Override
	public double calculatePerimeter() {
		return 2 * (getDistance() + height);
	}

}
