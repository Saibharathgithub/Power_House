package com.kanerika.powerhouse.Service;

import java.util.List;

import com.kanerika.powerhouse.Resource.MaildetailsPojo;

public interface EmailSenderService {

	String sendSimpleMail();

	public List<MaildetailsPojo> searchDetails(String query);

	// String SearchMailBody();


}
