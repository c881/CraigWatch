import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.AsyncResponse;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.Arrays;

public class SimpleEmailSender implements EmailSender {
    //http://www.simplejavamail.org/
    public EmailResponse send(EmailRequest emailRequest) {
//
        Email email = EmailBuilder.startingBlank()
                .to(emailRequest.to.name, emailRequest.to.toString())
                .from(emailRequest.from.name, emailRequest.from.toString())
                .withSubject(emailRequest.withSubject)
                .withHTMLText(emailRequest.withHTMLText)
                .withPlainText(emailRequest.withPlainText)
                .buildEmail();


        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "kobygs78@gmail.com", "20082010N")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withProperty("mail.smtp.sendpartial", true)
                .withDebugLogging(true)
                .buildMailer();

        mailer.sendMail(email);
//        AsyncResponse asyncResponse = mailer.sendMail(email, true);
//        asyncResponse.onSuccess(() -> System.out.println("Success"));
//        asyncResponse.onException((e) -> System.err.println("error"));
        EmailResponse emailResponse = new EmailResponse(true);

        return emailResponse;
    }
    public static void main(String[] args) {
        SimpleEmailSender try1 = new SimpleEmailSender();
        Contact to = new Contact("Jacov.g", Arrays.asList("jacov.g@gmail.com","amir.galanty@gmail.com"));
        Contact from = new Contact("Koby.gs", Arrays.asList("kobygs78@gmail.com"));
        EmailRequest emailRequest = new EmailRequestBuilder()
                .setFrom(from)
                .setTo(to)
                .setWithSubject("hey3")
                .setWithHTMLText("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>")
                .setWithPlainText("Please view this email in a modern email client!")
                .createEmailRequest();

        try1.send(emailRequest);

    }
}