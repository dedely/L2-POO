package td5.phonebook;

public class PhonebookTable implements PhonebookInterface {
    private Contact[] contacts;
    private int currentSize = 0;

    public PhonebookTable(int size) {
        contacts = new Contact[size];
    }

    public void add(Contact contact) {
        if (currentSize != contacts.length) {
            contacts[currentSize] = contact;
            currentSize++;
        }
    }

    public Contact searchByName(String name) {
        for (int index = 0; index < currentSize; index++) {
            if (contacts[index].getName().equals(name)) {
                return contacts[index];
            }
        }
        return null;
    }

    public Contact searchByNumber(String number) {
        for (int index = 0; index < currentSize; index++) {
            if (contacts[index].getNumber().equals(number)) {
                return contacts[index];
            }
        }
        return null;
    }

    public void modify(String name, String newNumber) {
        Contact contact = searchByName(name);
        if (contact != null) {
            contact.setNumber(newNumber);
        }
    }

    public void remove(String name) {
        int index;
        // Remove the contact
        for (index = 0; index < currentSize; index++) {
            if (contacts[index].getName().equals(name)) {
                contacts[index] = null;
                break;
            }
        }

        // Move the next contacts
        for (int newIndex = index; index < currentSize - 1; index++) {
            contacts[newIndex] = contacts[newIndex + 1];
        }

        if (index != currentSize) {
            currentSize--;
        }
    }

    @Override
    public String toString() {
        String result = "All contacts in phonebook : \n";
        for (int index = 0; index < currentSize; index++) {
            result += contacts[index].toString() + "\n";
        }
        return result;
    }
}
