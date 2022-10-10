package com.example.Mail_Database.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Mail_Database.Service.ServiceImplementation.EmailSenderServiceImpl;

@Repository
public class EmailRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private final EmailSenderServiceImpl emailSenderServiceImpl = null;

//get all Emails from database
	public List<String> getEmails() {

		List<String> emails = jdbcTemplate.queryForList("SELECT email_id FROM powerhouse", String.class);

		return emails;
	}

//	// get Email subjects
//	public List<String> getMailSubect() {
//
//		List<String> mailSubject = jdbcTemplate.queryForList("SELECT mail_subject FROM powerhouse", String.class);
//
//		return mailSubject;
//	}

	// get Email subject

	private static final String SQL_SELECT_SENT_MAIL_SUBJECT = "select mail_subject from  powerhouse WHERE email_id = :emailId";

	public String getMailSubject(String email) throws Exception {
		String mailSubject = null;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", email);

	          mailSubject = template.queryForObject(SQL_SELECT_SENT_MAIL_SUBJECT, params, String.class);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
		
		return mailSubject;

	}

	// get Email Body
//	public List<String> getMailBody()
//
//	{
//		List<String> mailBody = jdbcTemplate.queryForList("SELECT mail_body FROM powerhouse", String.class);
//
//		return mailBody;
//	}
	
	// get Email Body
	private static final String SQL_SELECT_SENT_MAIL_BODY = "select mail_body from  powerhouse WHERE email_id = :emailId";

	public String getMailBody(String email) throws Exception {
		String mailBody = null;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", email);

			 mailBody = template.queryForObject(SQL_SELECT_SENT_MAIL_BODY, params, String.class);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
		
		return mailBody;

	}

//update count in database
	private static final String SQL_UPDATE_SENT_MAIL_COUNT = "UPDATE  powerhouse SET mails_count =:mailCount  WHERE email_id = :emailId";
	private static final String SQL_SELECT_SENT_MAIL_COUNT = "select mails_count from  powerhouse WHERE email_id = :emailId";

	public void updateMailCount(String email) throws Exception {
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", email);

			Integer mailCount = template.queryForObject(SQL_SELECT_SENT_MAIL_COUNT, params, Integer.class);
			mailCount = mailCount + 1;

			params.addValue("mailCount", mailCount);

			template.update(SQL_UPDATE_SENT_MAIL_COUNT, params);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

	}

}