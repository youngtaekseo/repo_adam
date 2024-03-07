package com.ucl.infra.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class IndexController {

	@RequestMapping(value = "/indexSdm")
	public String codeXdmList() throws Exception {
		return Commvar.PATH_SDM + "indexSdm";
	}
}
