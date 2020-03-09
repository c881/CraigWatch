import java.util.Collection;

public class EmailRequest {
    Contact from;
    Collection<Contact> to;
    String withSubject;
    String withHTMLText;
    String withPlainText;

    public EmailRequest(Contact from, Collection<Contact> to, String withSubject, String withHTMLText, String withPlainText) {
        this.to = to;
        this.withSubject = withSubject;
        this.withHTMLText = withHTMLText;
        this.withPlainText = withPlainText;
        this.from = from;
    }
}
