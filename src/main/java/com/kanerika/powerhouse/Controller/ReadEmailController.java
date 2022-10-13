package com.kanerika.powerhouse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanerika.powerhouse.Service.ReadEmailInboxService;
import com.kanerika.powerhouse.Service.ServiceImplementation.ReadGmilInboxServiceimpl;

@RestController
@CrossOrigin
public class ReadEmailController {

	@Autowired
	ReadGmilInboxServiceimpl readGmilInboxServiceimpl;

	@Autowired
	ReadEmailInboxService emailService;

	@PostMapping("/test")
	public void test() {
		String host = "imap.gmail.com";
		String mailStoreType = "imap";
		String username = "chintalarajesh220@gmail.com";
		String password = "gsxtockogrnswrcb";


		readGmilInboxServiceimpl.check(host, mailStoreType, username, password);
	}

}
