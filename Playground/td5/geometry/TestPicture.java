package td5.geometry;

public class TestPicture {
	public static void main(String[] args) {
		Point point1 = new Point(1, 2);
		GeometricFigure disk = new Disk(point1, 5);

		Point point2 = new Point(3, 4);
		GeometricFigure square = new Square(point2, 5);

		Point point3 = new Point(5, 6);
		GeometricFigure rectangle = new Rectangle(point3, 5, 6);

		Picture picture = new Picture(5);
		picture.addFigure(disk);
		picture.addFigure(square);
		picture.addFigure(rectangle);

		System.out.println("Sum of surface : " + picture.sumSurface());

	}

}
