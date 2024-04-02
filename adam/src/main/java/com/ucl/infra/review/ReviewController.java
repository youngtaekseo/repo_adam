package com.ucl.infra.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class ReviewController {

	@Autowired
	ReviewService service;
	
	// 조회화면
	@RequestMapping(value = "/reviewUsrList")
	public String productSdmList(ReviewVo vo, Model model) throws Exception {
		model.addAttribute("list", service.selectList(vo));		
		return Commvar.PATH_PRODUCT + "productUsrDetail";
	}
	
}
