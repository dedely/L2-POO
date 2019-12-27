package td5.phonebook;

public class TestPhonebook {

    public static void main(String[] args) {
        PhonebookInterface phonebook = new PhonebookTable(50);
        Contact paul = new Contact("Paul", "123456", "paul@gmail.com");
        Contact jean = new Contact("Jean", "234567", "jean@hotmail.com");

        phonebook.add(paul);
        phonebook.add(jean);

        Contact foundPaul = phonebook.searchByName("Paul");
        System.out.println(foundPaul.toString());

        Contact foundJean = phonebook.searchByNumber("234567");
        System.out.println(foundJean.toString());

        System.out.println("-----------------------------");

        System.out.println("After modification : ");
        phonebook.modify("Paul", "222222");
        Contact foundPaul2 = phonebook.searchByName("Paul");
        System.out.println(foundPaul2.toString());

        System.out.println(phonebook.toString());

        System.out.println("-----------------------------");
        phonebook.remove("Paul");
        System.out.println("After removing : ");
        System.out.println(phonebook);
    }

}
