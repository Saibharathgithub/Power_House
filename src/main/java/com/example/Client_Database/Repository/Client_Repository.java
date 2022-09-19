package com.example.Client_Database.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Client_Database.Service.Client_Service;
import com.example.Client_Database.model.Table_Content;

@Repository
public class Client_Repository implements Client_Service {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public List<Table_Content> findAll() {
		List<Table_Content> query = jdbcTemplate.query("SELECT * FROM public.powerhouse", new BeanPropertyRowMapper<Table_Content>(Table_Content.class));
		return query;
	}

}
