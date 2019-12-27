package td5.geometry;

public abstract class GeometricFigure {
	private Point initialPoint;
	private int distance;

	public GeometricFigure(Point point, int distance) {
		this.initialPoint = point;
		this.distance = distance;
	}

	public abstract double calculateSurface();

	public abstract double calculatePerimeter();

	public Point getPoint() {
		return initialPoint;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "GeometricFigure [point=" + initialPoint + ", distance=" + distance + "]";
	}

}
