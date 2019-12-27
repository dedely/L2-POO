package td5.geometry;

public class Picture {
	private GeometricFigure[] figures;
	private int size;
	private int currentCount;

	public Picture(int size) {
		this.size = size;
		figures = new GeometricFigure[size];
		currentCount = 0;
	}

	public void addFigure(GeometricFigure figure) {
		if (currentCount < size) {
			figures[currentCount] = figure;
			currentCount++;
		}
	}

	public double sumSurface() {
		double result = 0.0f;
		for (int index = 0; index < currentCount; index++) {
			result += figures[index].calculateSurface();
		}
		return result;
	}

}
