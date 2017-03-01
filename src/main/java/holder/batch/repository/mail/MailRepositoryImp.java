package holder.batch.repository.mail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import holder.batch.mail.Mail;

@Repository
public class MailRepositoryImp implements MailRepository {
	private static final String MAIL_ID = "mail_id";
	private static final String TITLE = "title";
	private static final String SUBJECT = "subject";
	private static final String TEXT = "text";
	private static final String HEADER = "header";
	private static final String FOOTER = "footer";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public Mail getMailInfo(String mailId) {
		SqlParameterSource parameter = new MapSqlParameterSource()
				.addValue(MAIL_ID, mailId);
				
		String sql = "select * from Mail where mail_id = :mail_id";
		
		Mail mail = jdbcTemplate.queryForObject(sql, parameter, new MailRowMapper());
		return  mail;
	}
	
	class MailRowMapper implements RowMapper<Mail> {
		public Mail mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mail mail = Mail.builder()
					.title(rs.getString(TITLE))
					.subject(rs.getString(SUBJECT))
					.text(rs.getString(TEXT))
					.header(rs.getString(HEADER))
					.footer(rs.getString(FOOTER))
					.build();
			
			return mail;
		}
	}
}
