package com.kanerika.test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailRepository  {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	


	private static final String SQL_UPDATE_SENT_MAIL_COUNT = "UPDATE  powerhouse SET mails_count = mails_count+1  WHERE email_id = :emailId";

	public void updateMailCount(String mail1) throws Exception {
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", mail1);

			template.update(mail1, params);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

   }

	private static final String SQL_UPDATE_REPLY_SUBJECT = "UPDATE  powerhouse SET reply_subject = :replySubject  WHERE email_id = :emailId";

	public void updateReplySubject(String mail1, String emailSubject) throws Exception {
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", mail1);
			params.addValue("replySubject", emailSubject);

			template.update(SQL_UPDATE_REPLY_SUBJECT, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

	}
	
	
	
	
	private static final String SQL_UPDATE_REPLY_BODY = "UPDATE  powerhouse SET reply_body = :replyBody  WHERE email_id = :emailId";

	public void updateReplyBody(String mail1, String messageContent) throws Exception {
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", mail1);
			params.addValue("replyBody", messageContent);

			template.update(SQL_UPDATE_REPLY_BODY, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

	}
	
	
	
	
	
	
	
	

}
