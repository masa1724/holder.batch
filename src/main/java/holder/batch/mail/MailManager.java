package holder.batch.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import holder.batch.repository.mail.MailRepository;

public class MailManager {
	@Autowired
	private MailRepository mailRepository;
	
	public static void registerMail(String MailId, Mail mail) {
		
	}
	
	public static List<Mail> getMailList(String MailId, Mail mail) {
		List<Mail> mailList = new ArrayList<Mail>();
		
		
		return mailList;
	}
	
	public static String parseParameter(String text, Map<String,String> parameter) {
		String replacedText = "";
		
		// %key% を valueで置換
		for(Entry<String,String> entry : parameter.entrySet()) {
			replacedText = text.replace("%" + entry.getKey() + "%", entry.getValue());
		}
		
		return replacedText;
	}	
}
