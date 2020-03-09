package com.arobs.internship.librarymanagement.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

   // @Autowired
    MailSender mailSender;

    /**
     * @param toAddress    The email ID to send an email
     * @param emailSubject The email subject
     * @param emailMessage The email message
     */
    public void sendMail(String toAddress, String emailSubject, String emailMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("scridondarius255@gmail.com");
        mailMessage.setTo(toAddress);
        mailMessage.setSubject(emailSubject);
        mailMessage.setText(emailMessage);
        mailSender.send(mailMessage);
    }
}
