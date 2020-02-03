public class EmailRequest {
    String from;
    To to;
    String withSubject;
    String withHTMLText;
    String withPlainText;

    public EmailRequest(String from, To to, String withSubject, String withHTMLText, String withPlainText) {
        this.from = from;
        this.to = to;
        this.withSubject = withSubject;
        this.withHTMLText = withHTMLText;
        this.withPlainText = withPlainText;
    }
}
