package td5.geometry;

public class TestGeometricFigure {
	public static void main(String[] args) {
		Point point = new Point(1,2);
		
		GeometricFigure disk = new Disk(point, 5);
		System.out.println("Disk surface : " + disk.calculateSurface());
		System.out.println("Disk perimeter : " + disk.calculatePerimeter());
		
		GeometricFigure square = new Square(point, 5);
		System.out.println("Square surface : " + square.calculateSurface());
		System.out.println("Square perimeter : " + square.calculatePerimeter());
		
		GeometricFigure rectangle = new Rectangle(point, 5, 6);
		System.out.println("Rectangle surface : " + rectangle.calculateSurface());
		System.out.println("Rectangle perimeter : " + rectangle.calculatePerimeter());
	}

}
