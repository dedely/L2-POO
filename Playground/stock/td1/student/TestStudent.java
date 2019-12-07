package td1.student;

public class TestStudent {

	public static void main(String[] args) {
		Student jean = new Student("123", "Jean", "Dupont");
		Student paul = new Student("456", "Paul", "Dubois");
		
		jean.display();
		paul.display();
	}

}
