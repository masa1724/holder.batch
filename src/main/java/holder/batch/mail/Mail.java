package holder.batch.mail;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail {
	private String title;
	private String header;
	private String subject;
	private String text;
	private String footer;
	private Map<String,String> parameter;
}
