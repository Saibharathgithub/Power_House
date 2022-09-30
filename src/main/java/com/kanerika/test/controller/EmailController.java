package com.kanerika.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanerika.test.service.EmailService;
import com.kanerika.test.service.impl.ReadGmilInboxServiceimpl;

@RestController
public class EmailController {
	
	@Autowired
	ReadGmilInboxServiceimpl readGmilInboxServiceimpl;
	
	@Autowired
	EmailService emailService;

	@GetMapping("/test")
	public void test() {
		String host = "imap.gmail.com";
		String mailStoreType = "imap";
		String username = "chintalarajesh220@gmail.com";
		String password = "gsxtockogrnswrcb";

		readGmilInboxServiceimpl.check(host, mailStoreType, username, password);
	}

}
