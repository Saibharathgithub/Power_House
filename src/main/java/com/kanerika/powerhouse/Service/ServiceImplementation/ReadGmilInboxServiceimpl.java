package com.kanerika.powerhouse.Service.ServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

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

import com.kanerika.powerhouse.Repository.ReadEmailRepository;
import com.kanerika.powerhouse.Service.ReadEmailInboxService;

@Service
public class ReadGmilInboxServiceimpl implements ReadEmailInboxService {

	@Autowired
	private ReadEmailRepository emailRepository;

	public void check(String host, String storeType, String user, String password) {
		String replySubject = null;
		String mail1 = null;
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

				String messageBody = messageContent.replaceAll("\\<.*?\\>", " "); // i am interested On Thus 2022/10/06
				// System.out.println(messageBody);
				Pattern p = Pattern.compile("On [A-Za-z]+{3}, \\d{2} Oct, \\d{4}, \\d{1,3}:\\d{2}");

				String[] replySplit = p.split(messageBody); // i am interested, On Thus 2022/10/06 ....
				String ReplyBodyAfterSplitting = replySplit[0].toLowerCase(); // i am interested

				String[] positiveWords = { "interested", "agree", "happy", "yes" };
				String[] negativeWords = { "notinterested", "notagree", "no", "not" };
				String[] words = ReplyBodyAfterSplitting.split(" "); // i , am , interested
				String positive = "positive";
				String negative = "negative";

				List<String> list = new ArrayList<>();
				for (String word : words) {

					list.add(word);
				}
//				for (String wordsInList : list) {
//					System.out.println("words in list  " + wordsInList);
//				}
				for (int j = 0; j < positiveWords.length; j++) {
					if (list.contains(positiveWords[j])) {
						emailRepository.updateReplyStatus(mail1, positive);
						System.out.println("positive");

					}

					for (int k = 0; k < negativeWords.length; k++) {
						if (list.contains(negativeWords[k])) {
							emailRepository.updateReplyStatus(mail1, negative);
							System.out.println("negative");

						}
					}
				}

				System.out.println(" Message body: " + ReplyBodyAfterSplitting);
				emailRepository.updateReplyBody(mail1, ReplyBodyAfterSplitting);
//				emailRepository.updateMailCount(mail1);
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
