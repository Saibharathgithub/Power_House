package com.example.Mail_Database.Resource;

import lombok.Data;

@Data
public class EmailMessage {
	private String recipient;
<<<<<<< HEAD
	private String msgBody;
	private String subject;
=======
    private String msgBody;
    private String subject;
    private String attachment;
>>>>>>> branch 'mailsending' of https://github.com/Saibharathgithub/Power_House.git

<<<<<<< HEAD
	public EmailMessage(String recipient, String msgBody, String subject) {
=======
	
	    public EmailMessage(String recipient, String msgBody, String subject,String attachement) {
>>>>>>> branch 'mailsending' of https://github.com/Saibharathgithub/Power_House.git
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
		this.attachment=attachement;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
<<<<<<< HEAD

=======
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
		
>>>>>>> branch 'mailsending' of https://github.com/Saibharathgithub/Power_House.git
}
