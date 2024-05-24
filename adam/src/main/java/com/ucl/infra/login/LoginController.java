package com.ucl.infra.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Value("${my_email}")
	private String myEmail;
	
	@Value("${javascript_key}")
	private String javascriptKey;
	
	@Value("${kakao_redirect_uri}")
	private String kakaoRedirectUri;
	
	@Value("${kakao_location}")
	private String kakaoLocation;
	
	@Value("${naver_client_id}")
	private String naverClientId;

	@Value("${naver_callback_uri}")
	private String naverCallbackUri;	
	
	@Value("${google.client.id}")
	private String googleClientId;

	@Value("${google.client.secret}")
	private String googleClientSecret;

	@Value("${google.callback.uri}")
	private String googleCallbackUri;
	
	@Value("${google.scope}")
	private String scope;
	
	@Autowired
	LoginService service;
	
	// 관리자 로그인
	@RequestMapping(value = "/loginSdm")
	public String loginSdm(Model model) throws Exception {
		model.addAttribute("myEmail", myEmail);
		return Commvar.PATH_LOGIN + "loginSdm";
	}	
	
	// 관리자 로그인 화면에서 회원가입 클릭
	@RequestMapping(value = "/loginSdmMemberCreate")
	public String loginSdmMemberCreate() throws Exception {
		return Commvar.PATH_LOGIN + "loginSdmMemberCreate";
	}	
	
	// 사용자 로그인
	@RequestMapping(value = "/loginUsr")
	public String loginUsr(Model model) throws Exception {
		// email%20profile%20openid
		scope = scope.replaceAll(",", "%20");
		String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri="+googleCallbackUri+"&response_type=code&scope="+scope+"&access_type=offline";
        
		model.addAttribute("myEmail"           , myEmail);
    	model.addAttribute("javascriptKey"     , javascriptKey);
    	model.addAttribute("kakaoRedirectUri"  , kakaoRedirectUri);
    	model.addAttribute("kakaoLocation"     , kakaoLocation);
    	model.addAttribute("naverClientId"     , naverClientId);
    	model.addAttribute("naverCallbackUri"  , naverCallbackUri);
    	model.addAttribute("googleClientId"    , googleClientId);
    	model.addAttribute("googleClientSecret", googleClientSecret);
    	model.addAttribute("googleCallbackUri" , googleCallbackUri);
    	model.addAttribute("googleLocation"    , reqUrl);
		return Commvar.PATH_LOGIN + "loginUsr";
	}
	
	// 세션저장
	@RequestMapping(value = "/loginSession")
	public Map<String, Object> loginSession(LoginDto dto, HttpSession httpSession) throws Exception {
		// 회원정보조회
		dto = service.selectOne(dto); 
		
		httpSession.setMaxInactiveInterval(60 * Commvar.SESSION_MINUTE_SDM); // 60second * 30 = 30minute
		httpSession.setAttribute("sessMbrSeq"  , dto.getMbrSeq());		// 회원순번
		httpSession.setAttribute("sessMbrEmail", dto.getMbrEmail());	// 회원이메일
		httpSession.setAttribute("sessMbrName" , dto.getMbrName());		// 회원명
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("rt", "success");
		returnMap.put("sessMbrName", dto.getMbrName());
		
		return returnMap;
	}
}
