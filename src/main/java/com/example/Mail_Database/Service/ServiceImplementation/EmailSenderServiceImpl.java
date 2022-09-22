package com.example.Mail_Database.Service.ServiceImplementation;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Mail_Database.Repository.EmailRepository;
import com.example.Mail_Database.Service.EmailSenderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	private final EmailRepository emailRepository;
	
	@Autowired
	public EmailSenderServiceImpl(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Value("${spring.mail.username}")
	private String sender;

	public String sendSimpleMail() {		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		List<String> emails = emailRepository.getEmails();
		for (String email : emails) {
			mailMessage.setFrom(sender);
			mailMessage.setTo(email);
			mailMessage.setText("foo");
			mailMessage.setSubject("foo");
			try {
				javaMailSender.send(mailMessage);
				System.out.println("sent to" + email);
			} catch (Exception e) {
				return "Error while Sending Mail";
			}
		}
		return "All Mails Sents Successfully...";
	}

}
