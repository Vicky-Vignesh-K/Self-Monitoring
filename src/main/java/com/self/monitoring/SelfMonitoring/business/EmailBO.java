package com.self.monitoring.SelfMonitoring.business;


import com.self.monitoring.SelfMonitoring.dto.EmailCredentialsVO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailBO {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public boolean sendEmail(EmailCredentialsVO emailCredentialsVO) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        if(emailCredentialsVO != null) {
            simpleMailMessage.setTo(emailCredentialsVO.getRecipientEmailAddress());
            simpleMailMessage.setFrom(senderEmail);
            simpleMailMessage.setSentDate(new Date());
            simpleMailMessage.setSubject(emailCredentialsVO.getSubject());
            simpleMailMessage.setText(emailCredentialsVO.getMessage());
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        return false;
    }

    public boolean sendEmailWithAttachment(EmailCredentialsVO emailCredentialsVO){

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(senderEmail);
            mimeMessageHelper.setTo(emailCredentialsVO.getRecipientEmailAddress());
            mimeMessageHelper.setSubject(emailCredentialsVO.getSubject());
            mimeMessageHelper.setText(emailCredentialsVO.getMessage(),true);
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.addAttachment(emailCredentialsVO.getFile().getOriginalFilename(),emailCredentialsVO.getFile());
            javaMailSender.send(mimeMessage);
            System.out.println("Mail sent with attachment successfully");
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
