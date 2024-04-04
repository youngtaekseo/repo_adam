package com.ucl.infra.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

import jakarta.servlet.http.HttpSession;

@Controller
public class CheckOutController {

	@Autowired
	CheckOutService service;
	
	// 결제하기
	@RequestMapping(value = "/productUsrCheckOut")
	public String productUsrCheckOut(@ModelAttribute("vo") CheckOutVo vo, HttpSession httpSession, Model model) throws Exception {
		// 로그인 회원순번 설정
		vo.setShSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 자료조회
		model.addAttribute("list", service.selectListWishList(vo));
		
		// 찜내역 건수 및 합계금액
		model.addAttribute("item", service.selectOneWisilistCount(vo));
		
		return Commvar.PATH_PRODUCT + "productUsrCheckOut";
	}
}
