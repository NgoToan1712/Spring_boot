package com.company.opentalk.controller;

import com.company.opentalk.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

//    @RequestMapping("/send-mail")
//    public void sendEmail() throws MessagingException {
//         mailService.sendEmail();
//    }
}
