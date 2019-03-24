package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Connection {
	public static String sendMail(String content) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.gmail.com");
		//props.setProperty("mail.smtp.auth", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Authenticator authenticator = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("tyrantviru@gmail.com", "kgagltyixqntzadq");
			}
		};


		Session session = Session.getDefaultInstance(props, authenticator);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("tyrantviru@gmail.com"));
		message.setRecipient(RecipientType.TO, new InternetAddress("s3675917@student.rmit.edu.au"));
		message.setSubject("port selection");
		String str = "{"+content+"}";
		message.setContent(str, "text/html;charset=UTF-8");

		Transport.send(message);
		System.out.println("message sent");
		return "message sent";
	}
}
