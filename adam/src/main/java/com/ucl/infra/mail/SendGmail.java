package com.ucl.infra.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendGmail {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
    	simpleMailMessage.setTo("ramses69@naver.com");
    	simpleMailMessage.setSubject("안녕하세요");
//    	simpleMailMessage.setFrom("wjsgusfhr324@gmail.com"); 
    	simpleMailMessage.setText("안녕하세요\n\n 잘지내고 계시죠");

    	javaMailSender.send(simpleMailMessage);
	}
}
