import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EmailRequestBuilder {
    private Contact from;
//    private Contact to;
    private List<Contact> to;
//    private Recipient from;
//    private List<Recipient> toList;
    private String withSubject;
    private String withHTMLText;
    private String withPlainText;

    public EmailRequestBuilder setFrom(Contact from) {
        this.from = from;
        return this;
    }

    public EmailRequestBuilder setTo(Contact...to) {
        if(this.to == null){
            this.to = new LinkedList<>();
        }
        this.to.addAll(Arrays.asList(to));
        return this;
    }


    public EmailRequestBuilder setWithSubject(String withSubject) {
        this.withSubject = withSubject;
        return this;
    }

    public EmailRequestBuilder setWithHTMLText(String withHTMLText) {
        this.withHTMLText = withHTMLText;
        return this;
    }

    public EmailRequestBuilder setWithPlainText(String withPlainText) {
        this.withPlainText = withPlainText;
        return this;
    }

    public EmailRequest createEmailRequest() {
        return new EmailRequest(from, to, withSubject, withHTMLText, withPlainText);
    }
}