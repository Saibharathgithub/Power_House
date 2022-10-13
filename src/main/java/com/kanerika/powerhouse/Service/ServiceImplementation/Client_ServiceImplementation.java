package com.kanerika.powerhouse.Service.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanerika.powerhouse.Repository.Client_Repository;
import com.kanerika.powerhouse.Resource.Table_Content;

@Service
public class Client_ServiceImplementation {
	
	@Autowired
	private Client_Repository client_repository;
	
	
	public List<Table_Content> findAll() {
		
		return client_repository.findAll();
	}

}
