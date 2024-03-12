package com.ucl.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class IndexController {

	@Autowired
	IndexService service;
	
	@RequestMapping(value = "/indexSdm")
	public String indexSdm() throws Exception {
		return Commvar.PATH_HOME + "indexSdm";
	}
}
