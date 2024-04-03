package com.ucl.infra.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class LoginController {

	@Autowired
	LoginService service;
	
	// 관리자 로그인
	@RequestMapping(value = "/loginSdm")
	public String loginSdm() throws Exception {
		return Commvar.PATH_LOGIN + "loginSdm";
	}	
	
	// 사용자 로그인
	@RequestMapping(value = "/loginUsr")
	public String loginUsr() throws Exception {
		return Commvar.PATH_LOGIN + "loginUsr";
	}
}
