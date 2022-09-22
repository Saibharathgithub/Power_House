package com.example.Mail_Database.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Mail_Database.Service.EmailSenderService;

@RestController
public class EmailController {
 
    @Autowired 
    private EmailSenderService emailService;
 
   
    @PostMapping("/sendMail")
    public String sendMail(){
    	return emailService.sendSimpleMail();
    }
 
}
