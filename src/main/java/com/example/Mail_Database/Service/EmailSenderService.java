package com.example.Mail_Database.Service;

import java.util.List;

import com.example.Mail_Database.Resource.MaildetailsPojo;

public interface EmailSenderService {

	String sendSimpleMail();

	public List<MaildetailsPojo> searchDetails(String query);

	// String SearchMailBody();

//	 String sendMailwithAttachment();
}
