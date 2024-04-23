package com.ucl.infra.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	LoginService service;
	
	// 관리자 로그인
	@RequestMapping(value = "/loginSdm")
	public String loginSdm(HttpSession httpSession) throws Exception {
		httpSession.setAttribute("sessWorker", "sdm");
		return Commvar.PATH_LOGIN + "loginSdm";
	}	
	
	// 관리자 로그인 화면에서 회원가입 클릭
	@RequestMapping(value = "/loginSdmMemberCreate")
	public String loginSdmMemberCreate() throws Exception {
		return Commvar.PATH_LOGIN + "loginSdmMemberCreate";
	}	
	
	// 사용자 로그인
	@RequestMapping(value = "/loginUsr")
	public String loginUsr() throws Exception {
		return Commvar.PATH_LOGIN + "loginUsr";
	}
}
