package com.self.monitoring.SelfMonitoring.service;

import com.self.monitoring.SelfMonitoring.business.EmailBO;
import com.self.monitoring.SelfMonitoring.configuration.ResponseStructure;
import com.self.monitoring.SelfMonitoring.dto.EmailCredentialsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

    @Autowired
    private EmailBO emailBO;

    @Autowired
    private ResponseStructure responseStructure;

    public ResponseEntity<ResponseStructure<EmailCredentialsVO>> sendEmail(EmailCredentialsVO emailCredentialsVO){
        if(emailBO.sendEmail(emailCredentialsVO)){
            responseStructure.setData(emailCredentialsVO);
            responseStructure.setMessage("Mail Sent Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<EmailCredentialsVO>>(responseStructure,HttpStatus.OK);
        }else{
            responseStructure.setData(emailCredentialsVO);
            responseStructure.setMessage("Error in sending Mail with Attachment");
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<ResponseStructure<EmailCredentialsVO>>(responseStructure,HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<ResponseStructure<EmailCredentialsVO>> sendEmailWithAttachment(String recipientEmailAddress, String message, String subject, MultipartFile multipartFile){
        EmailCredentialsVO emailCredentialsVO = new EmailCredentialsVO();
        emailCredentialsVO.setRecipientEmailAddress(recipientEmailAddress);
        emailCredentialsVO.setMessage(message);
        emailCredentialsVO.setSubject(subject);
        emailCredentialsVO.setFile(multipartFile);
        if(emailBO.sendEmailWithAttachment(emailCredentialsVO)){
            emailCredentialsVO.setFile(null);
            responseStructure.setData(emailCredentialsVO);
            responseStructure.setMessage("Mail Sent Successfully with Attachment");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<EmailCredentialsVO>>(responseStructure,HttpStatus.OK);
        }else{
            emailCredentialsVO.setFile(null);
            responseStructure.setData(emailCredentialsVO);
            responseStructure.setMessage("Error in sending Mail with Attachment");
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<ResponseStructure<EmailCredentialsVO>>(responseStructure,HttpStatus.NOT_FOUND);
        }
    }

}
