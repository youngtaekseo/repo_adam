package com.ucl.infra.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	@RequestMapping(value = "/productSdmList")
	public String productSdmList(@ModelAttribute("vo") ProductVo vo, Model model) {
//		model.addAttribute("list", service.s)
		return Commvar.PATH_PRODUCT + "productSdmList";
	}
}
