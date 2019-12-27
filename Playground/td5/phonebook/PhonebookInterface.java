package td5.phonebook;

public interface PhonebookInterface {
    public void add(Contact contact);

    public Contact searchByName(String name);

    public Contact searchByNumber(String number);

    public void modify(String name, String newNumber);

    public void remove(String name);

}
