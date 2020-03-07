import java.util.Collection;
//
//import java.util.List;

public class EmailRequest {
    Contact from;
//    Contact to;
    Collection<Contact> toList;
//    Recipient from;
//    Collection<Recipient> to;
    String withSubject;
    String withHTMLText;
    String withPlainText;

    public EmailRequest(Contact from, Collection<Contact> toList, String withSubject, String withHTMLText, String withPlainText) {
//public EmailRequest(Recipient from, Collection<Recipient> to, String withSubject, String withHTMLText, String withPlainText) {
        this.from = from;
        this.toList = toList;
        this.withSubject = withSubject;
        this.withHTMLText = withHTMLText;
        this.withPlainText = withPlainText;
    }
}
