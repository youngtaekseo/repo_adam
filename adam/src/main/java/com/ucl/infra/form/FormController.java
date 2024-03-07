package com.ucl.infra.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class FormController {

	@RequestMapping(value = "formSdm")
	public String forms() {
		return Commvar.PATH_SDM + "formSdm";
	}
}
