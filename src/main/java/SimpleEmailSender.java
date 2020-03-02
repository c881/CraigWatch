import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.Recipient;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.mail.Message;

public class SimpleEmailSender implements EmailSender {
    //http://www.simplejavamail.org/
    public EmailResponse send(EmailRequest emailRequest) {
//
        Email email = EmailBuilder.startingBlank()
//                .to(emailRequest.to.name, emailRequest.to.eAddress)
                .to(emailRequest.to)
//                .from(emailRequest.from.name, emailRequest.from.eAddress)
                .from(emailRequest.from)
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
        EmailResponse emailResponse = new EmailResponse(true);

        return emailResponse;
    }
    public static void main(String[] args) {
        SimpleEmailSender try1 = new SimpleEmailSender();
        Recipient toJacov = new Recipient("Jacov.g", "jacov.g@gmail.com", Message.RecipientType.TO);
        Recipient toAmir = new Recipient("Jacov.g", "amir.galanty@gmail.com", Message.RecipientType.TO);
        Recipient from = new Recipient("Koby.gs", "kobygs78@gmail.com", Message.RecipientType.TO);
        EmailRequest emailRequest = new EmailRequestBuilder()
                .setFrom(from)
//                .setTo(toJacov, toAmir)
                .to(toAmir).to(toJacov)
                .to(toAmir, toJacov)
                .setWithSubject("hey hello")
                .setWithHTMLText("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>")
                .setWithPlainText("Please view this email in a modern email client!")
                .createEmailRequest();

        try1.send(emailRequest);

    }
}