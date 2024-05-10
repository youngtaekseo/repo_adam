package com.ucl.infra.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ucl.infra.member.MemberDto;

@Service
public class SendGmail {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(MemberDto dto) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
    	simpleMailMessage.setTo("ramses69@naver.com");
    	simpleMailMessage.setSubject("회원가입 안내");
//    	simpleMailMessage.setFrom("wjsgusfhr324@gmail.com"); 
    	simpleMailMessage.setText(dto.getMbrName() + "님\n\n 안녕하세요\n 회원가입 축하드립니다\n\n 유익한 사이트가 되도록 노력하겠습니다");

    	javaMailSender.send(simpleMailMessage);
	}
}
