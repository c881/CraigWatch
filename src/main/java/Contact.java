import java.util.List;

public class Contact{
    String name;
    String address;

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", eAddress='" + address + '\'' +
                '}';
    }

    public Contact(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
