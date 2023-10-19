package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.MailerName;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/smtp/send")
public class GreetingResource {

    @Inject
    @MailerName("smtp1")
    Mailer mailer1;

    @Inject
    @MailerName("smtp2")
    Mailer mailer2;

    @GET
    @Path("1")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendSmtp1() {
	this.mailer1.send(Mail.withText("mail1@quarkus.io", "WARNING: Using SMTP1", "This mail should fail with smtp 1"));
	return "Send email with smtp 1";
    }

    @GET
    @Path("2")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendSmtp2() {
	this.mailer2.send(Mail.withText("mail2@quarkus.io", "WARNING: Using SMTP2", "This mail should arrive with smtp 2"));
	return "Send email with smtp 2";
    }
}
