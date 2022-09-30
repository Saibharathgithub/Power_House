package com.example.Mail_Database.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mail_Database.Resource.EmailMessage;
import com.example.Mail_Database.Service.EmailSenderService;

@RestController
public class EmailController {
 
    @Autowired 
    private EmailSenderService emailService;
    
//    @PostMapping("/sendMailwithAttachment") 
//    public String sendMailWithAttachment(@RequestBody EmailMessage details)
//    {
//        String status = emailService.sendMailwithAttachment(details);
//        return status;
//    }
//}
 
   
//    @PostMapping("/sendMail")
//    public String sendMail(){
//    	return emailService.sendSimpleMail();
//    }
}
