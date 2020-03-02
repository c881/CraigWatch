import org.simplejavamail.api.email.Recipient;

import java.util.Collection;
import java.util.List;
//
//import java.util.List;

public class EmailRequest {
//    Contact from;
//    Contact to;
    Recipient from;
    Collection<Recipient> to;
    String withSubject;
    String withHTMLText;
    String withPlainText;

//    public EmailRequest(Contact from, Contact to, String withSubject, String withHTMLText, String withPlainText) {
public EmailRequest(Recipient from, Collection<Recipient> to, String withSubject, String withHTMLText, String withPlainText) {
        this.from = from;
        this.to = to;
        this.withSubject = withSubject;
        this.withHTMLText = withHTMLText;
        this.withPlainText = withPlainText;
    }
}