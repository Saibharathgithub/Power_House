package com.kanerika.powerhouse.Service.ServiceImplementation;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kanerika.powerhouse.Repository.MaildetailsRepository;
import com.kanerika.powerhouse.Repository.SendEmailRepository;
import com.kanerika.powerhouse.Resource.MaildetailsPojo;
import com.kanerika.powerhouse.Service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private final SendEmailRepository emailRepository;
	@Autowired
	private MaildetailsRepository maildetailsRepository;

	@Autowired
	public EmailSenderServiceImpl(SendEmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${spring.mail.password}")
	private String password;

	List<String> sendingMails = new LinkedList<>();

	@Override

	public String sendSimpleMail() {
		sendingMails.clear();
		List<String> emails = emailRepository.getEmails();

		// List<Integer> emailsCount = emailRepository.getMailsCount();

		List<String> diffrentDomains = new LinkedList<>();
		Map<String, LinkedList<String>> searchMap = new HashMap<>();

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);

			}
		});

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

		for (int b = 0; b < diffrentDomains.size(); b++) {
			LinkedList<String> finalMail = searchMap.get(diffrentDomains.get(b));
			String mailSubject = null;
			String mailBody = null;
			int count = 0; // count for checking how many mails sending for domain
			for (int c = 0; c < finalMail.size(); c++) {

				// Start our mail message
				MimeMessage msg = new MimeMessage(session);

				try {
					int checkSendMailCount = emailRepository.checkMailCount(finalMail.get(c));

					if (!(checkSendMailCount > 0)) {
						msg.setFrom(new InternetAddress(sender));
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(finalMail.get(c)));
						mailSubject = emailRepository.getMailSubject(finalMail.get(c).toString());
						msg.setSubject(mailSubject);

						Multipart emailContent = new MimeMultipart();

						// Text body part
						MimeBodyPart textBodyPart = new MimeBodyPart();
						mailBody = emailRepository.getMailBody(finalMail.get(c).toString());
						textBodyPart.setText(mailBody);

						// Attachment body part.
						MimeBodyPart pdfAttachment = new MimeBodyPart();
						pdfAttachment.attachFile("C:\\Users\\Admin\\Downloads\\cskvsrcb.jpg");

						// Attach body parts
						emailContent.addBodyPart(textBodyPart);
						emailContent.addBodyPart(pdfAttachment);

						// Attach multipart to message
						msg.setContent(emailContent);

						Transport.send(msg);
						System.out.println("Sent message" + finalMail.get(c));
						sendingMails.add(finalMail.get(c));
						// update mail count
						emailRepository.updateMailCount(finalMail.get(c));
						// update send mail date
						emailRepository.updateSendMailDate(finalMail.get(c));
						count = count + 1;
					}
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (count == 5) {
					break;
				}

			}
		}

		return "send to: " + sendingMails.toString();

	}

	@Override
	public List<MaildetailsPojo> searchDetails(String query) {

		List<MaildetailsPojo> detailsList = maildetailsRepository.searchDetails(query);

		return detailsList;

	}

}
