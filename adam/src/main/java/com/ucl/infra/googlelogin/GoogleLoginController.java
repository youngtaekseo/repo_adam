package com.ucl.infra.googlelogin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ucl.common.constants.Commvar;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
public class GoogleLoginController {
	@Value("${google.client.id}")
	private String googleClientId;

	@Value("${google.client.secret}")
	private String googleClientSecret;

	@Value("${google.callback.uri}")
	private String googleCallbackUri;
	
	@Autowired
	GoogleLoginService service;
	
	@RequestMapping(value="/loginUrlGoogle", method = RequestMethod.POST)
    public String loginUrlGoogle() throws Exception{
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri="+googleCallbackUri+"&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return reqUrl;
    }
	
    @RequestMapping(value="/googlelogin", method = RequestMethod.GET)
    public String googlelogin(@RequestParam(value = "code") String authCode, HttpSession httpSession, GoogleRequest dto, GoogleLoginDto sDto, GoogleLoginDto rDto) throws Exception{
    	dto.setClientId(googleClientId);
    	dto.setClientSecret(googleClientSecret);
    	dto.setCode(authCode);
    	dto.setRedirectUri(googleCallbackUri);
    	dto.setGrantType("authorization_code");
    	
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequest googleOAuthRequestParam = dto;
        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);
        String jwtToken=resultEntity.getBody().getId_token();
        System.out.println("jwtToken: " +jwtToken);
        Map<String, String> map=new HashMap<>();
        map.put("id_token",jwtToken);
        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfResponse.class);
        String email = resultEntity2.getBody().getEmail();
        String name = resultEntity2.getBody().getName();
        
        rDto.setEmail(email);
        rDto.setName(name);
        
    	// 회원확인
    	sDto = service.selectOneLogin(rDto);
        
        if(sDto == null) {
        	rDto.setMbrType(2); //1:관리자, 2:사용자
        	service.insert(rDto);    		
        	httpSession.setAttribute("sessMbrSeq"  , rDto.getMbrSeq());
        	httpSession.setAttribute("sessMbrEmail", rDto.getEmail());
        	httpSession.setAttribute("sessMbrName" , rDto.getName());	        	
        } else {
        	httpSession.setAttribute("sessMbrSeq"  , sDto.getMbrSeq());
        	httpSession.setAttribute("sessMbrEmail", sDto.getMbrEmail());
        	httpSession.setAttribute("sessMbrName" , sDto.getMbrName());
        }
        
        return Commvar.PATH_HOME + "indexUsr";
    }
}
