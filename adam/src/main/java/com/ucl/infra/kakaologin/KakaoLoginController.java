package com.ucl.infra.kakaologin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoLoginController {
	@Value("${kakao_rest_key}")
	private String kakaoRestKey;
	
	@Autowired
	KakaoLoginService service;
    
	// 토큰받기, 사용저정보 등록
    @RequestMapping(value="/redirectKakao")
    public String loginKakaoRedirect(KakaoLoginDto dto, KakaoLoginDto isDto, HttpSession httpSession) throws Exception {
    	// 토큰 받기 
    	String accessToken = service.getAccessTokenFromKakao(kakaoRestKey, dto.getCode());
		
    	dto = service.getUserInfo(accessToken, dto);	  
    	
    	// 회원확인
    	isDto = service.selectOneLogin(dto);
        
        if(isDto == null) {
        	dto.setMbrType(1); // 사용자
        	service.insert(dto);    		
        	httpSession.setAttribute("sessMbrSeq"  , dto.getMbrSeq());
        	httpSession.setAttribute("sessMbrEmail", dto.getEmail());
        	httpSession.setAttribute("sessMbrName" , dto.getName());	        	
        } else {
        	httpSession.setAttribute("sessMbrSeq"  , isDto.getMbrSeq());
        	httpSession.setAttribute("sessMbrEmail", isDto.getMbrEmail());
        	httpSession.setAttribute("sessMbrName" , isDto.getMbrName());
        }
        
        return "redirect:/indexUsr";
    }
    
}
