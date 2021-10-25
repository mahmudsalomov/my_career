package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;



    void sendEmail(String toAccount, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAccount); // example@gmail.com
        msg.setSubject("My career validation"); // Subject of mail
        msg.setText("For validation please click this link - " + text); // Content of mail
        javaMailSender.send(msg);
    }
}
