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
	private String sendTo;
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
	
	public void sendMailToClientComplaint(String response, String refund, String theUser, String theComplaint, String theLotName){
		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(this.sendTo));
			message.setSubject("CPS customer service response");
			message.setText("Dear " + theUser + ",\n\nYour complaint:\n" + theComplaint
				+"\n\nThe response: \n" + response + "\n\nYour refund: " + refund +" NIS.\n\nThank you, "
						+ "and have a nice day.\nCPS Customer Service.");
	
			Transport.send(message);
	
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
