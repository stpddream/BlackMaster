package org.phillyvip.pocketvip.data;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.phillyvip.pocketvip.test.VIPTest;

import android.util.Log;

public class VIPFlyer extends Authenticator {
	private String mailhost = "smtp.gmail.com";
	private String user;
	private String password;
	private Session session;
	
	
	public VIPFlyer(String user, String passwd) {
		this.user = user;
		this.password = passwd;
		
		Properties props = new Properties();
		
		//props.setProperty("mail.transport.protocol", "smtp");
		//props.setProperty("mail.host", mailhost);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port","465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
		
		
		session = Session.getDefaultInstance(props, this);
	    
	    
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
	
	public synchronized void sendMail(String subject, String body,
			                                                              String sender, String recipient) throws AddressException,
			                                                                                                                                            MessagingException {	
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setSubject(subject);
			message.setText(body);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			Transport.send(message);
			Log.i(VIPTest.TESTTAG, "Email sent");
			
	}
	
	


}
