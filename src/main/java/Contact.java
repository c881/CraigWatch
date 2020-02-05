public class Contact {
    String name;
    String eAddress;

    @Override
    public String toString() {
        return "Contact{" +
                "eAddress='" + eAddress + '\'' +
                '}';
    }


    public Contact(String name, String eAddress) {
        this.name = name;
        this.eAddress = eAddress;
    }

}
