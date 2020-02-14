import org.simplejavamail.api.email.Recipient;

public class EmailRequest { //    Contact from;
    Contact from;
    Contact to;
    String withSubject;
    String withHTMLText;
    String withPlainText;

    public EmailRequest(Contact from, Contact to, String withSubject, String withHTMLText, String withPlainText) {
        this.from = from;
        this.to = to;
        this.withSubject = withSubject;
        this.withHTMLText = withHTMLText;
        this.withPlainText = withPlainText;
    }
}
