package com.insurance.utitlity;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils 
{
	@Autowired
     private JavaMailSender sender;
	
	public boolean sendEmail(String sub , String body , String to , File f) throws MessagingException {
		
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper help = new MimeMessageHelper(mm , true);
		
		help.setSubject(sub);
		help.setText(body , true);
		help.setTo(to);
		help.addAttachment("Plans", f);
		
		sender.send(mm);
		
		  return true;
	}
}
