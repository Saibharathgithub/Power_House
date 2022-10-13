package com.kanerika.powerhouse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//github.com/Saibharathgithub/Power_House.git
import org.springframework.web.bind.annotation.RestController;

import com.kanerika.powerhouse.Resource.MaildetailsPojo;
import com.kanerika.powerhouse.Service.EmailSenderService;

@RestController
@CrossOrigin(origins = "*")
public class SendEmailController {

	@Autowired
	private EmailSenderService emailService;

	@GetMapping("/search")
	public ResponseEntity<List<MaildetailsPojo>> searchDetails(@RequestParam("query") String query) {

		return ResponseEntity.ok(emailService.searchDetails(query));

	}

	@GetMapping("/sendMail")
	public ResponseEntity<String> sendSimpleMail() {
		return ResponseEntity.ok(emailService.sendSimpleMail());

	}

}
