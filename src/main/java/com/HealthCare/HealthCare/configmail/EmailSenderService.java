package com.HealthCare.HealthCare.configmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderService {

    @Autowired(required = true)
    private JavaMailSender mailSender;

    public void sendEmail(String email,String subject,String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("healthcareputltd99@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        System.out.println("sucessfull");
    }
}
