package com.self.monitoring.SelfMonitoring.controller;

import com.self.monitoring.SelfMonitoring.configuration.ResponseStructure;
import com.self.monitoring.SelfMonitoring.dto.EmailCredentialsVO;
import com.self.monitoring.SelfMonitoring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("sendMail")
    public ResponseEntity<ResponseStructure<EmailCredentialsVO>> sendMail(@RequestBody EmailCredentialsVO emailCredentialsVO){
            return emailService.sendEmail(emailCredentialsVO);
    }

    @PostMapping("sendMailWithAttachment")
    public ResponseEntity<ResponseStructure<EmailCredentialsVO>> sendMailWithAttachment(@RequestParam String recipientEmailAddress, @RequestParam String message, @RequestParam String subject, @RequestPart MultipartFile multipartFile){
        return emailService.sendEmailWithAttachment(recipientEmailAddress,message,subject,multipartFile);
    }

}
