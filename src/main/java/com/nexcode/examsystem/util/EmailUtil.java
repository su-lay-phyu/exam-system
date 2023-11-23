package com.nexcode.examsystem.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil{

	public final JavaMailSender javaMailSender;
	
	public EmailUtil(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	public void sendEmail(String to, String subject, String content) throws MessagingException 
	{
		
		MimeMessage message = javaMailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("eduzone2023.mm@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		javaMailSender.send(message); 
	}

}
