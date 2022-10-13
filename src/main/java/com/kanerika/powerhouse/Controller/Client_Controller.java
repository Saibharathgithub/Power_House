package com.kanerika.powerhouse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanerika.powerhouse.Resource.Table_Content;
import com.kanerika.powerhouse.Service.ServiceImplementation.Client_ServiceImplementation;

@RestController
public class Client_Controller {
    @Autowired
	private Client_ServiceImplementation client_serviceimplementation;
	
	@GetMapping("/getAll")
	public List<Table_Content> findAll() {
		return client_serviceimplementation.findAll();
	}

}
