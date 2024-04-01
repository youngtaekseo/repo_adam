package com.ucl.infra.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class IndexController {

	// 메인화면
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		return Commvar.PATH_HOME + "index";
	}

	// 관리자메인
	@RequestMapping(value = "/indexSdm")
	public String indexSdm() throws Exception {
		return Commvar.PATH_HOME + "indexSdm";
	}
	
	// 사용자메인
	@RequestMapping(value = "/indexUsr")
	public String indexUsr() throws Exception {
		return Commvar.PATH_HOME + "indexUsr";
	}
}
