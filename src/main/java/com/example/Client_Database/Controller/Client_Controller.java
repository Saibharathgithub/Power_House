package com.example.Client_Database.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Client_Database.Service.ServiceImplementation.Client_ServiceImplementation;
import com.example.Client_Database.model.Table_Content;

@RestController
public class Client_Controller {
    @Autowired
	private Client_ServiceImplementation client_serviceimplementation;
	
	@GetMapping("/getAll")
	public List<Table_Content> findAll() {
		return client_serviceimplementation.findAll();
	}

}
