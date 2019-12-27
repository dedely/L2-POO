package td5.geometry;

public class Disk extends GeometricFigure{

	public Disk(Point point, int distance) {
		super(point, distance);
	}

	@Override
	public double calculateSurface() {
		return Math.PI * getDistance() * getDistance();
	}

	@Override
	public double calculatePerimeter() {
		return 2 * Math.PI * getDistance();
	}

}
