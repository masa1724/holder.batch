package holder.batch.mail;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import holder.batch.repository.mail.MailRepository;

@Component
public class RealTimeMailSender {
	private static final String HOST = "mail.smtp.host";
	private static final String STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String AUTH = "mail.smtp.auth";
	private static final String PORT = "mail.smtp.port";
	private static final String DEBUG = "mail.smtp.debug";
	
	private static final String GMAIL_USER = "gmail.user";
	private static final String GMAIL_PASSWORD = "gmail.password";
	private static final String SENDER_MAIL_ADDRESS = "sender.mail_address";
	private static final String SENDER_NAME = "sender.name";
	
	private static final String SUBJECT_ENCODING = "subject.encoding";
	private static final String TEXT_ENCODING = "text.encoding";
	
	@Autowired
	private Properties mailProperties;
	
	@Autowired
	private MailRepository mailRepository;
	
	public void send(String mailId, Map<String,String> textParameter) throws IOException {
		final String title = "";
		final String message = "";
		String reciveMailAddr = "";
		String reciveName = "";
		
		Properties authProperties = new Properties();
		authProperties.setProperty(AUTH, mailProperties.getProperty(AUTH));
		authProperties.setProperty(STARTTLS_ENABLE, mailProperties.getProperty(STARTTLS_ENABLE));
		authProperties.setProperty(HOST, mailProperties.getProperty(HOST));
		authProperties.setProperty(PORT, mailProperties.getProperty(PORT));
		authProperties.setProperty(DEBUG, mailProperties.getProperty(DEBUG));
		
		Session session = Session.getInstance(authProperties, new javax.mail.Authenticator(){
		    protected PasswordAuthentication getPasswordAuthentication(){
		        return new PasswordAuthentication(mailProperties.getProperty(GMAIL_USER), 
		        							      mailProperties.getProperty(GMAIL_PASSWORD));
		    }
		});

		try {
			// 送信先アドレス・名前の設定
			final InternetAddress toAddress = 
			        new InternetAddress(reciveMailAddr, reciveName);
			
			// 送信元アドレス・名前の設定
			final InternetAddress fromAddress = 
			        new InternetAddress(mailProperties.getProperty(SENDER_MAIL_ADDRESS),
			        		            mailProperties.getProperty(SENDER_NAME));
			
			
			MimeMessage mimeMessage = new MimeMessage(session){{
				setRecipient(Message.RecipientType.TO, toAddress);
				setFrom(fromAddress);
				setSubject(title, mailProperties.getProperty(SUBJECT_ENCODING));
				setText(message, mailProperties.getProperty(TEXT_ENCODING));
			}};
		
			Transport.send(mimeMessage);	
		} catch (SendFailedException e) {
		    Address[] sentAddresses = e.getValidSentAddresses();
		    Address[] invalidAddresses = e.getInvalidAddresses();
		    Address[] unsentAddresses = e.getValidUnsentAddresses();
		    
		    
			e.printStackTrace();
		} catch (MessagingException e) {
		    e.printStackTrace();
		}
	}
}
