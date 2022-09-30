package com.example.Mail_Database;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.Mail_Database.Resource.EmailMessage;
import com.example.Mail_Database.Service.ServiceImplementation.EmailSenderServiceImpl;

@SpringBootApplication
@EnableScheduling
public class MailDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailDatabaseApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		sender.sendSimpleMail(null);
//		
//	}

}
