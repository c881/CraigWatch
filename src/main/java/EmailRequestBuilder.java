import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EmailRequestBuilder {
    private Contact from;
//    private Contact to;
    private List<Contact> toList;
//    private Recipient from;
//    private List<Recipient> toList;
    private String withSubject;
    private String withHTMLText;
    private String withPlainText;

    public EmailRequestBuilder setFrom(Contact from) {
//    public EmailRequestBuilder setFrom(Recipient from) {
        this.from = from;
        return this;
    }

//    public EmailRequestBuilder setTo(Recipient to) {
    public EmailRequestBuilder to(Contact...toList) {
        if(this.toList == null){
            this.toList = new LinkedList<>();
        }
        this.toList.addAll(Arrays.asList(toList));
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
        return new EmailRequest(from, toList, withSubject, withHTMLText, withPlainText);
    }
}