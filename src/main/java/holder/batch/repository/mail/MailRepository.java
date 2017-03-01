package holder.batch.repository.mail;

import holder.batch.mail.Mail;

public interface MailRepository {
	public Mail getMailInfo(String mailId);
}
