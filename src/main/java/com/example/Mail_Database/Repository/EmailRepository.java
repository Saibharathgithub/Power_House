package com.example.Mail_Database.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public List<String> getEmails() {
		List<String> emails = jdbcTemplate.queryForList("SELECT email_id FROM powerhouse", String.class);
		System.out.println(emails);
		return emails;
	}

}
