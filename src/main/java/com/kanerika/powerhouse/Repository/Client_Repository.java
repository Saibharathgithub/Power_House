package com.kanerika.powerhouse.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kanerika.powerhouse.Resource.Table_Content;
import com.kanerika.powerhouse.Service.Client_Service;

@Repository
public class Client_Repository implements Client_Service {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Table_Content> findAll() {
		List<Table_Content> query = jdbcTemplate.query("SELECT * FROM public.powerhouse",
				new BeanPropertyRowMapper<Table_Content>(Table_Content.class));
		return query;
	}

}
