package com.kanerika.test.service.impl;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanerika.test.repository.EmailRepository;
import com.kanerika.test.service.EmailService;

@Service
public class ReadGmilInboxServiceimpl implements EmailService {

	@Autowired
	private EmailRepository emailRepository;

	public void check(String host, String storeType, String user, String password) {
	String	 replySubject = null;
		String mail1=null;
		String messageContent = null;

		try {

			// create properties
			Properties properties = new Properties();

			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", "993");
			properties.put("mail.imap.starttls.enable", "true");
			properties.put("mail.imap.ssl.trust", host);

			Session emailSession = Session.getDefaultInstance(properties);

			// create the imap store object and connect to the imap server
			Store store = emailSession.getStore("imaps");

			store.connect(host, user, password);

			// create the inbox object and open it
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
			System.out.println("messages.length---" + messages.length);

			String ReplyFrom;
			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				message.setFlag(Flag.SEEN, true);
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));

       		replySubject = message.getSubject();
				
				System.out.println("ReplySubject: " + replySubject);

				ReplyFrom = message.getFrom()[0].toString();
				// System.out.println("ReplyFrom: " + ReplyFrom);

				String[] replymail = ReplyFrom.split("<");

				String mail = replymail[1];
				String[] replymail1 = mail.split(">");
				mail1 = replymail1[0];
				System.out.println(" reply from :" + mail1);

				try {

					emailRepository.updateReplySubject(mail1, replySubject);
				} catch (Exception e) {
					e.printStackTrace();
				}

				String contentType = message.getContentType();
				// String messageContent = "";

				if (contentType.contains("multipart")) {
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						messageContent = part.getContent().toString();
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();

					}
				}
				System.out.println(" Message body: " + messageContent.replaceAll("\\<.*?\\>", " "));
				emailRepository.updateReplyBody(mail1, messageContent.replaceAll("\\<.*?\\>", " "));
				emailRepository.updateMailCount(mail1);
				emailRepository.updateReplyDate(mail1);

			}

			inbox.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
