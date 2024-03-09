package com.ucl.infra.list;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class ListController {

	@RequestMapping(value = "/listSdm")
	public String account() {
		return Commvar.PATH_SDM_SC + "listSdm";
	}
}
