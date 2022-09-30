package com.example.Mail_Database.Service.ServiceImplementation;

import java.io.IOException;
import java.util.List;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.Mail_Database.Repository.EmailRepository;
import com.example.Mail_Database.Service.EmailSenderService;
import com.google.api.client.util.Value;

@Service
public class EmailSenderServiceImpl implements EmailSenderService  {

	@Autowired
	private JavaMailSender javaMailSender;

	private final EmailRepository emailRepository;

	@Autowired
	public EmailSenderServiceImpl(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Value("${spring.mail.username}")
	private String sender;
	@Override
	@Scheduled(fixedRate = 50000)
	public String sendSimpleMail() {
		List<String> emails = emailRepository.getEmails();

		for(String email : emails) {
		final String username = "chintalarajesh220@gmail.com";
		final String password = "gsxtockogrnswrcb";
		String fromEmail = "chintalarajesh220@gmail.com";
		
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			msg.setSubject("IPL PREDICTION");
			
			Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("Who will win the match tonight");
			
			//Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile("C:\\Users\\Admin\\Downloads\\ipl 1.jpg");
			
			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			
			//Attach multipart to message
			msg.setContent(emailContent);
			
			Transport.send(msg);
			System.out.println("Sent message" +email);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return "Mail Sent Successfully";
	}
}

//	@Scheduled(fixedRate = 50000)
//	public String sendSimpleMail()  {	
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
////		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		List<String> emails = emailRepository.getEmails();
//		try {
//			for (String email : emails) {
//				mimeMessageHelper.setFrom(sender);
//				mimeMessageHelper.setTo(email);
//				mimeMessageHelper.setText("WHO WILL WIN THE MATCH TONIGHT");
//				mimeMessageHelper.setSubject("IPL PREDICTION");
//				File file = new File ("C:\\Users\\Admin\\Downloads\\ipl.jpg");
//				mimeMessageHelper.addAttachment("ipl",file);
//				javaMailSender.send(mimeMessage);
//				System.out.println("sent to" + email);
//			}
//		} catch (Exception e) {
//			return "Error while Sending Mail";
//		}
//		return "All Mails Sents Successfully...";
//	}
//	@Scheduled(fixedRate = 5000)
//	public String sendMailwithAttachment() {
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper;
//		List<String> emails = emailRepository.getEmails();
//
//		for (String email : emails) {
//			try {
//				mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//				mimeMessageHelper.setFrom(sender);
//				mimeMessageHelper.setTo(email);
//				mimeMessageHelper.setText("WHO WILL WIN THE MATCH TONIGHT");
//				mimeMessageHelper.setSubject("IPL PREDICTION");
//
////				FileSystemResource file = new FileSystemResource("C:\\Users\\Admin\\Downloads");
////
////				mimeMessageHelper.addAttachment(ipl., file);
//				javaMailSender.send(mimeMessage);
//				System.out.println("sent to" + email);
//
//				javaMailSender.send(mimeMessage);
//				return "Mail sent Successfully";
//			}
//
//			catch (MessagingException e) {
//
//				return "Error while sending mail!!!";
//			}
//		}
//		return "messages sent successfully";
//	}
//}
//	
//	private static final String APPLICATION_NAME = "Gmail API java ";
//	private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//	private static final String user = "me";
//	static Gmail service = null;
//	private static File filePath = new File("C:\\Users\\Admin\\Documents\\workspace-spring-tool-suite-4-4.15.3.RELEASE\\Mail_Database\\Credentials.json");
//
//	public static void main(String[] args) throws IOException, GeneralSecurityException {
//
//		getGmailService();
//		
//		getMailBody("Google");
//
//	}
//
//	public static void getMailBody(String searchString) throws IOException {
//
//		// Access Gmail inbox
//
//		Gmail.Users.Messages.List request = service.users().messages().list(user).setQ(searchString);
//
//		ListMessagesResponse messagesResponse = request.execute();
//		request.setPageToken(messagesResponse.getNextPageToken());
//
//		// Get ID of the email you are looking for
//		String messageId = messagesResponse.getMessages().get(0).getId();
//
//		Message  message = service.users().messages().get(user, messageId).execute();
//
//		// Print email body
//
//		String emailBody = StringUtils.newStringUtf8(Base64.getDecoder());
//
//		System.out.println("Email body : " + emailBody);
//
//	}
//
//	public static Gmail getGmailService() throws IOException, GeneralSecurityException {
//
//		InputStream in = new FileInputStream("C:\\Users\\Admin\\Documents\\workspace-spring-tool-suite-4-4.15.3.RELEASE\\Mail_Database\\Credentials.json"); // Read credentials.json
//		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//		// Credential builder
//
//		Credential authorize = new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
//				.setJsonFactory(JSON_FACTORY)
//				.setClientSecrets(clientSecrets.getDetails().getClientId().toString(),
//						clientSecrets.getDetails().getClientSecret().toString())
//				.build().setAccessToken(getAccessToken()).setRefreshToken(
//						"ya29.a0Aa4xrXNY3KO9038Ih7r7XIZ-RK-Cjy-fgv11WSZ3CrMzz4V3aRQ8MR6sgBZy5uFXWezsCcaKkG9BjrGvCXqQR9BhpiB2gJ6SNY1mKJIuN0oCVCOBRMXkfAWy26vaUKqfQHzP643BhpLan95mQ9yxvfEDRnzYaCgYKATASARMSFQEjDvL9o6P9yKBuIPLJEoyqBVU57w0163");//Replace this
//
//		// Create Gmail service
//		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//		service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, authorize)
//				.setApplicationName(EmailSenderServiceImpl.APPLICATION_NAME).build();
//
//		return service;
//	}
//
//	private static String getAccessToken() {
//
//		try {
//			Map<String, Object> params = new LinkedHashMap<>();
//			params.put("grant_type", "refresh_token");
//			params.put("client_id", "328873573247-cu2ueq0air480o3hk6447s48efaohsgu.apps.googleusercontent.com"); //Replace this
//			params.put("client_secret", "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"); //Replace this
//			params.put("refresh_token",
//					"ya29.a0Aa4xrXNY3KO9038Ih7r7XIZ-RK-Cjy-fgv11WSZ3CrMzz4V3aRQ8MR6sgBZy5uFXWezsCcaKkG9BjrGvCXqQR9BhpiB2gJ6SNY1mKJIuN0oCVCOBRMXkfAWy26vaUKqfQHzP643BhpLan95mQ9yxvfEDRnzYaCgYKATASARMSFQEjDvL9o6P9yKBuIPLJEoyqBVU57w0163"); //Replace this
//
//			StringBuilder postData = new StringBuilder();
//			for (Map.Entry<String, Object> param : params.entrySet()) {
//				if (postData.length() != 0) {
//					postData.append('&');
//				}
//				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//				postData.append('=');
//				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//			}
//			byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//
//			URL url = new URL("https://accounts.google.com/o/oauth2/token");
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setDoOutput(true);
//			con.setUseCaches(false);
//			con.setRequestMethod("POST");
//			con.getOutputStream().write(postDataBytes);
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			StringBuffer buffer = new StringBuffer();
//			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
//				buffer.append(line);
//			}
//
//			JSONObject json = new JSONObject(buffer.toString());
//			String accessToken = json.getString("access_token");
//			return accessToken;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return null;
//	}

//	private static final Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);
//	@Scheduled(fixedRate = 7000)
//	public void readInboundEmails(){
//	//create session object
//	Session session =this.getImapSession();
//	try{
//	//connect to message store
//	Store store = session.getStore("imap");
//	store.connect("smtp.gmail.com",587, "chintalarajesh220@gmail.com", "gsxtockogrnswrcb");
//	//open the inbox folder
//	IMAPFolder inbox = (IMAPFolder)store.getFolder("INBOX");
//	inbox.open(Folder.READ_WRITE);
//	//fetch messages
//	Message[] messages = inbox.getMessages();
//	//read messages
//	for (int i = 0; i < messages.length; i++) {
//	Message msg = messages[i];
//	Address[] fromAddress = msg.getFrom();
//	String from = fromAddress[0].toString();
//	String subject = msg.getSubject();
//	Address[] toList = msg.getRecipients(RecipientType.TO);
//	Address[] ccList = msg.getRecipients(RecipientType.CC);
//	String contentType = msg.getContentType();
//	}
//	}
//	catch (AuthenticationFailedException e){
//	logger.error("Exception in reading EMails : " +e.getMessage());
//	}
//	catch (MessagingException e) {
//	logger.error("Exception in reading EMails : " +e.getMessage());
//	}
//	catch (Exception e) {
//	logger.error("Exception in reading EMails : " +e.getMessage());
//	}
//	}
//	@Scheduled(fixedRate = 7000)
//	private Session getImapSession(){
//	Properties props = new Properties();
//	props.setProperty("mail.store.protocol","imap");
//	props.setProperty("mail.debug", "true");
//	props.setProperty("mail.imap.host","smtp.gmail.com");
//	props.setProperty("mail.imap.port", "587");
//	props.setProperty("mail.imap.ssl.enable","true");
//	Session session = Session.getDefaultInstance(props, null);
//	session.setDebug(true);
//	return session;
//	}
