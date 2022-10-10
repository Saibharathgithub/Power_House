package com.example.Mail_Database.Service.ServiceImplementation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Mail_Database.Repository.EmailRepository;
import com.example.Mail_Database.Repository.MaildetailsRepository;
import com.example.Mail_Database.Resource.MaildetailsPojo;
import com.example.Mail_Database.Service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private final EmailRepository emailRepository;
	@Autowired
	private MaildetailsRepository maildetailsRepository;

	@Autowired
	public EmailSenderServiceImpl(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Value("${spring.mail.username}")
	private String sender;
	List<String> sendingMails = new LinkedList<>();

	public String sendSimpleMail() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		List<String> emails = emailRepository.getEmails();

		// List<String> emailSubjects = emailRepository.getMailSubect();
//		 List<String> emailBody = emailRepository.getMailBody();
		// List<Integer> emailsCount = emailRepository.getMailsCount();

		List<String> diffrentDomains = new LinkedList<>();
		Map<String, LinkedList<String>> searchMap = new HashMap<>();

		// to divide domains
		for (int j = 0; j < emails.size(); j++) {
			String email = emails.get(j);
			int index = emails.get(j).indexOf('@');
			email = email.substring(index);
			// System.out.println(" email domains : " + email);
			if (!diffrentDomains.contains(email)) {
				diffrentDomains.add(email);
			}
		}
		System.out.println("diffrentDomains : " + diffrentDomains);

		for (String dffDomains : diffrentDomains) {
			for (String emailsFromDb : emails) {
				if (!searchMap.containsKey(dffDomains)) {
					searchMap.put(dffDomains, new LinkedList<String>());
				}
				if (emailsFromDb.contains(dffDomains)) {
					searchMap.get(dffDomains).add(emailsFromDb);
				}
			}
		}

//		for (String iKey : searchMap.keySet()) {
//			System.out.println(iKey + ": " + searchMap.get(iKey).toString());
//		}

		for (int b = 0; b < diffrentDomains.size(); b++) {
			LinkedList<String> finalMail = searchMap.get(diffrentDomains.get(b));
			String mailSubject = null;
			String mailBody = null;

			for (int c = 0; c < finalMail.size(); c++) {
				mailMessage.setFrom(sender);
				mailMessage.setTo(finalMail.get(c));
				try {
					mailBody = emailRepository.getMailBody(finalMail.get(c).toString());
					mailMessage.setText(mailBody);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
				try {
					mailSubject = emailRepository.getMailSubject(finalMail.get(c).toString());
					mailMessage.setSubject(mailSubject);
				} catch (Exception e1) {
					return "error in mail ";
				}
				try {
					javaMailSender.send(mailMessage);
					System.out.println("sent to : " + finalMail.get(c));
					sendingMails.add(finalMail.get(c));
					emailRepository.updateMailCount(finalMail.get(c));
				} catch (Exception e) {
					return "Error while Sending Mail";
				}
				if (c == 4) {
					break;
				}
			}
		}

		return sendingMails.toString();

	}

	@Override
	public List<MaildetailsPojo> searchDetails(String query) {

		List<MaildetailsPojo> detailsList = maildetailsRepository.searchDetails(query);

		return detailsList;

	}

}
