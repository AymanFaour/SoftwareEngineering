package model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class CpsMailBox {

	private String username;
	private String password;
	public String sendTo;
	Properties props;
	Session session;
	
	public CpsMailBox(String un, String ps, String to){
		this.username = un;
		this.password = ps;
		this.sendTo = to;
		this.props = new Properties();
		this.props.put("mail.smtp.auth", "true");
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.host", "smtp.gmail.com");
		this.props.put("mail.smtp.port", "587");
	
		this.session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
	}
	
	public void sendMail(String response){
		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(this.sendTo));
			message.setSubject("Testing Subject");
			message.setText(response);
	
			Transport.send(message);
	
			System.out.println("Done");
	
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
