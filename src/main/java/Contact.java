import java.util.List;

public class Contact {
    String name;
    List<String> eAddresses;

    @Override
    public String toString() {
        return String.join(" ,",this.eAddresses);
    }



    public Contact(String name, List<String> eAddresses) {
        this.name = name;
        this.eAddresses = eAddresses;
    }

}
