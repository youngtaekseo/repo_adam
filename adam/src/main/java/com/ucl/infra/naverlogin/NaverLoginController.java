package com.ucl.infra.naverlogin;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class NaverLoginController {
	@Autowired
	NaverLoginService service;
	
	@Value("${naver_client_id}")
    private String naverClientId;	
	@Value("${naver_client_secret}")
    private String naverClientSecret;	
	@Value("${naver_redirect_uri}")
    private String naverRedirectUri;
	
    // 랜덤 문자열 생성
    public String randomString() {
	    int leftLimit  = 48;  // '0'
	    int rightLimit = 122; // 'z'
	    int stringLength = 10; // 문자열길이
	    Random random = new Random();
	    
	    String rt = random.ints(leftLimit, rightLimit+1)
	    		.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	    		.limit(stringLength)
	    		.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    		.toString();
	    
    	return rt;
    }
    
	@RequestMapping(value="/loginNaver")
	public String loginNaver(HttpServletRequest request) {
	    //String state = RandomStringUtils.randomAlphabetic(10);   // 랜덤 문자열 생성
	    
	    // 랜덤 문자열 생성
	    String state = randomString();
	    
	    String login_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
	            + "&client_id="    + naverClientId
	            + "&redirect_uri=" + naverRedirectUri
	            + "&state="        + state;

	    request.getSession().setAttribute("state", state);
	    
	    return "redirect:" + login_url;
	}
	
	@RequestMapping(value="/redirectNaver")
	public String redirectNaver(HttpServletRequest request, NaverLoginDto dto, NaverLoginDto isDto, 
			Model model, HttpSession httpSession) {
		// 네이버에서 전달해준 code, state 값 가져오기
	    String code  = request.getParameter("code");
	    String state = request.getParameter("state");

	    // 세션에 저장해둔 state값 가져오기
	    String session_state = String.valueOf(request.getSession().getAttribute("state"));

		// CSRF 공격 방지를 위해 state 값 비교
	    if (!state.equals(session_state)) {
	        System.out.println("세션 불일치");
	        request.getSession().removeAttribute("state");
	        return "/error";
	    }

	    String tokenURL = "https://nid.naver.com/oauth2.0/token";

	    // body data 생성
	    MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
	    parameter.add("grant_type"   , "authorization_code");
	    parameter.add("client_id"    , naverClientId);
	    parameter.add("client_secret", naverClientSecret);
	    parameter.add("code"         , code);
	    parameter.add("state"        , state);

	    // request header 설정
	    HttpHeaders headers = new HttpHeaders();
	    // Content-type을 application/x-www-form-urlencoded 로 설정
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    // header 와 body로 Request 생성
	    HttpEntity<?> entity = new HttpEntity<>(parameter, headers);

	    try {
	        RestTemplate restTemplate = new RestTemplate();
	        // 응답 데이터(json)를 Map 으로 받을 수 있도록 관련 메시지 컨버터 추가
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	        // Post 방식으로 Http 요청
	        // 응답 데이터 형식은 Hashmap 으로 지정
	        ResponseEntity<HashMap> result = restTemplate.postForEntity(tokenURL, entity, HashMap.class);
	        Map<String, String> resMap = result.getBody();

	        // 리턴받은 access_token 가져오기
	        String access_token = resMap.get("access_token");

	        String userInfoURL = "https://openapi.naver.com/v1/nid/me";
	        // Header에 access_token 삽입
	        headers.set("Authorization", "Bearer "+access_token);

	        // Request entity 생성
	        HttpEntity<?> userInfoEntity = new HttpEntity<>(headers);

	        // Post 방식으로 Http 요청
	        // 응답 데이터 형식은 Hashmap 으로 지정
	        ResponseEntity<HashMap> userResult = restTemplate.postForEntity(userInfoURL, userInfoEntity, HashMap.class);
	        Map<String, Object> userResultMap = userResult.getBody();
	        
	        //응답 데이터 확인
	        //System.out.println(userResultMap);
	        
		    /*{response={
		    id=YosQCAcb_GcHkIxD9bfiJe7HgRnf_Fm8vH7dtHmL74U, 
		    email=abc@naver.com, 
		    mobile=010-1234-5678, 
		    mobile_e164=+821012345678, 
		    name=홍길동}, 
		    resultcode=00, 
		    message=success}
			*/
	        
	        Map<String, String> response = (Map<String, String>) userResultMap.get("response");
	        dto.setEmail(response.get("email"));

	        // 회원존재확인
	        isDto = service.selectOneLogin(dto);
	        
	        if(isDto == null) {
	        	dto.setId(response.get("id"));
	        	dto.setMbrType(1); // 사용자
	        	dto.setEmail(response.get("email"));
	        	dto.setMobile(response.get("mobile"));
	        	dto.setName(response.get("name"));

	        	// 회원등록
	        	service.insert(dto);
	        	
	        	httpSession.setAttribute("sessMbrSeq"  , dto.getMbrSeq());
	        	httpSession.setAttribute("sessMbrEmail", dto.getEmail());
	        	httpSession.setAttribute("sessMbrName" , dto.getName());	        	
	        } else {
	        	httpSession.setAttribute("sessMbrSeq"  , isDto.getMbrSeq());
	        	httpSession.setAttribute("sessMbrEmail", isDto.getMbrEmail());
	        	httpSession.setAttribute("sessMbrName" , isDto.getMbrName());
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		// 세션에 저장된 state 값 삭제
	    request.getSession().removeAttribute("state");
	    
	    return "redirect:/indexUsr";
	}
	
	// 네이버 로그인
	@ResponseBody
	@RequestMapping(value = "/naverLoginInsert")
	public Map<String, Object> naverLoginInsert(NaverLoginDto dto, NaverLoginDto isDto, HttpSession httpSession) {
		Map<String, Object> returnMap = new HashMap<>();
		
        // 회원존재확인
		isDto = service.selectOneLogin(dto);
        
        if(isDto == null) {
        	dto.setMbrType(1); // 사용자
        	
        	// 회원등록
    		if(service.insert(dto) == 1) {
	        	httpSession.setAttribute("sessMbrSeq"  , dto.getMbrSeq());
	        	httpSession.setAttribute("sessMbrEmail", dto.getEmail());
	        	httpSession.setAttribute("sessMbrName" , dto.getName());
	        	
    			returnMap.put("rt", "success");
    		} else {
    			returnMap.put("rt", "fail");
    		}
        } else {
        	httpSession.setAttribute("sessMbrSeq"  , isDto.getMbrSeq());
        	httpSession.setAttribute("sessMbrEmail", isDto.getMbrEmail());
        	httpSession.setAttribute("sessMbrName" , isDto.getMbrName());
        	
        	returnMap.put("rt", "success");
        }
        
		return returnMap;
	}
}
