package com.ucl.infra.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class FormController {

	@RequestMapping(value = "formSdmList")
	public String formSdmList() {
		return Commvar.PATH_TEMP + "formSdmList";
	}

}
