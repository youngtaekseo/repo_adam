package com.ucl.infra.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class LoginController {

	@RequestMapping(value = "loginSdm")
	public String login() throws Exception {
		return Commvar.PATH_SDM + "loginSdm";
	}	
}
