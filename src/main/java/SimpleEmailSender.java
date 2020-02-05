import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class SimpleEmailSender implements EmailSender {
    //http://www.simplejavamail.org/
    public EmailResponse send(EmailRequest emailRequest) {
//        Email email = EmailBuilder.startingBlank()
//                .to("jacov", "jacov.g@gmail.com")
//                .from("kobygs78@gmail.com")
////                .to("C. Cane", "candycane@candyshop.org")
////                .ccWithFixedName("C. Bo group", "chocobo1@candyshop.org", "chocobo2@candyshop.org")
//               // .withRecipientsUsingFixedName("Tasting Group", BCC,
//               //         "taster1@cgroup.org;taster2@cgroup.org;tester <taster3@cgroup.org>")
////                .bcc("Mr Sweetnose <snose@candyshop.org>")
////                .withReplyTo("lollypop", "lolly.pop@othermail.com")
//                .withSubject("hey")
//                .withHTMLText("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>")
//                .withPlainText("Please view this email in a modern email client!")
//        //        .withCalendar(CalendarMethod.REQUEST, iCalendarText)
//        //        .withEmbeddedImage("wink1", imageByteArray, "image/png")
//        //        .withEmbeddedImage("wink2", imageDatesource)
//        //        .withAttachment("invitation", pdfByteArray, "application/pdf")
//        //        .withAttachment("dresscode", odfDatasource)
////                .withHeader("X-Priority", 5)
////                .withReturnReceiptTo()
////                .withDispositionNotificationTo("notify-read-emails@candyshop.com")
////                .withBounceTo("tech@candyshop.com")
////                .signWithDomainKey(privateKeyData, "somemail.com", "selector") // DKIM
////                .signWithSmime(pkcs12Config)
////                .encryptWithSmime(x509Certificate)
//                .buildEmail();

        Email email = EmailBuilder.startingBlank()
                .to(emailRequest.to.name, emailRequest.to.eAddress)
                .from(emailRequest.from.name, emailRequest.from.eAddress)
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

        return null;
    }
    public static void main(String[] args) {
        SimpleEmailSender try1 = new SimpleEmailSender();
        Contact to = new Contact("Jacov.g", "jacov.g@gmail.com");
        Contact from = new Contact("Koby.gs", "kobygs78@gmail.com");
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


