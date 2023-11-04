package com.nexcode.examsystem.component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class EmailComponent{

	public final JavaMailSender javaMailSender;
	
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
