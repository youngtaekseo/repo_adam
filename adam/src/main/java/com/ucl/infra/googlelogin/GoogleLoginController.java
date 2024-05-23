package com.ucl.infra.googlelogin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
public class GoogleLoginController {
	@Value("${google.client.id}")
	private String googleClientId;

	@Value("${google.client.secret}")
	private String googleClientSecret;

	@Value("${google.callback.uri}")
	private String googleCallbackUri;
	
	@RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.POST)
    public String loginUrlGoogle(){
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri="+googleCallbackUri+"&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return reqUrl;
    }
	
    @RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode) throws Exception{
    	System.out.println("============================================loginGoogle");
    	System.out.println("============================================authCode: "+authCode);
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequest googleOAuthRequestParam = new GoogleRequest
        		.GoogleRequestBuilder()
        		.setClientId(googleClientId)
                .setClientSecret(googleClientSecret)
                .setCode(authCode)
                .setRedirectUri(googleCallbackUri)
                .setGrantType("authorization_code")
                .build();
        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);
        String jwtToken=resultEntity.getBody().getId_token();
        System.out.println("jwtToken: " +jwtToken);
        Map<String, String> map=new HashMap<>();
        map.put("id_token",jwtToken);
        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfResponse.class);
        String email=resultEntity2.getBody().getEmail();    
        System.out.println("구글로그인 이메일: " +email);
        return email;
    }
}
