package com.kanerika.powerhouse.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReadEmailRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	// update reply subject
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

	// update reply body

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

	// update reply date
	private static final String SQL_UPDATE_REPLY_DATE = "UPDATE  powerhouse SET replied_date = CURRENT_TIMESTAMP  WHERE email_id = :emailId";

	public void updateReplyDate(String mail1) throws Exception {
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", mail1);

			template.update(SQL_UPDATE_REPLY_DATE, params);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

	}

// update reply status positive or negative

	private static final String SQL_UPDATE_REPLY_STATUS = "UPDATE  powerhouse SET reply_status = :replyStatus  WHERE email_id = :emailId";

	public void updateReplyStatus(String mail1, String status) throws Exception {
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("emailId", mail1);
			params.addValue("replyStatus", status);

			template.update(SQL_UPDATE_REPLY_STATUS, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

	}

}
