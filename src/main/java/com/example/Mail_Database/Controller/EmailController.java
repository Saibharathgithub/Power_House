package com.example.Mail_Database.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mail_Database.Resource.MaildetailsPojo;
import com.example.Mail_Database.Service.EmailSenderService;

@RestController
public class EmailController {

	@Autowired
	private EmailSenderService emailService;

	@GetMapping("/search")
	public ResponseEntity<List<MaildetailsPojo>> searchDetails(@RequestParam("mail_body") String query) {

		return ResponseEntity.ok(emailService.searchDetails(query));

	}

	@GetMapping("/sendMail")
	public ResponseEntity<String> sendSimpleMail() {
		return ResponseEntity.ok(emailService.sendSimpleMail());

	}

}
