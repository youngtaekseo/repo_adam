package com.ucl.infra.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/memberSdmList")
	public String memberSdmList() {
		return Commvar.PATH_MEMBER + "memberSdmList";
	}
}
