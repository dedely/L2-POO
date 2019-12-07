package td1.student;

public class Student {

	private String number;
	private String firstname;
	private String lastname;

	public Student(String num, String first, String last) {
		number = num;
		firstname = first;
		lastname = last;
	}

	public void display() {
		System.out.println("Student [number=" + number + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]");
	}
}
