import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.EmailPopulatingBuilder;
import org.simplejavamail.api.email.Recipient;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.mail.Message;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleEmailSender implements EmailSender {
    //http://www.simplejavamail.org/
    public EmailResponse send(EmailRequest emailRequest) {
//
        EmailPopulatingBuilder emailPopulatingBuilder = EmailBuilder.startingBlank();
        Collection<Contact> tos = emailRequest.to;
        List<Recipient> recipients = tos.stream().map(to -> convert(to)).collect(Collectors.toList());
        emailPopulatingBuilder.to(recipients);
//        emailPopulatingBuilder.from(convert(emailRequest.from));
        emailPopulatingBuilder.withSubject(emailRequest.withSubject);
        emailPopulatingBuilder.withHTMLText(emailRequest.withHTMLText);
        emailPopulatingBuilder.withPlainText(emailRequest.withPlainText);//                .to(emailRequest.to.name, emailRequest.to.eAddress)
//                .from(emailRequest.from.name, emailRequest.from.eAddress)
        Email email = emailPopulatingBuilder
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
        EmailResponse emailResponse = new EmailResponse(true);

        return emailResponse;
    }
    public static void main(String[] args) {
        SimpleEmailSender try1 = new SimpleEmailSender();
        Contact toJacov = new Contact("Jacov.g", "jacov.g@gmail.com");
        Contact toAmir = new Contact("Jacov.g", "amir.galanty@gmail.com");
//        Contact toNamesList = new Contact("all","jacov.g@gmail.com;amir.galanty@gmail.com",
//                Message.RecipientType.TO);
        Contact from = new Contact("Koby.gs", "kobygs78@gmail.com");
        EmailRequest emailRequest = new EmailRequestBuilder()
//                .setFrom(from)
//                .setTo(toJacov)
//                .to(toNamesList)
                .setWithSubject("hey hello")
                .setWithHTMLText("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>")
                .setWithPlainText("Please view this email in a modern email client!")
                .createEmailRequest();

        try1.send(emailRequest);

    }
    public Recipient convert(Contact contact){
        Recipient recipient = new Recipient(contact.name, contact.address, Message.RecipientType.TO);
        return recipient;
    }
}