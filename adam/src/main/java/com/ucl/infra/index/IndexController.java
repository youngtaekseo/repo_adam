package com.ucl.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;
import com.ucl.infra.product.ProductService;
import com.ucl.infra.product.ProductVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	@Autowired
	ProductService productService;

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
	public String indexUsr(ProductVo vo, Model model, HttpSession httpSession) throws Exception {
		
		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 추천차량
		vo.setShNewRegNy(0);
		vo.setShRecommend(17);
		model.addAttribute("listRecommend", productService.selectListCarInfo(vo));
		
		// 최근등록차량
		vo.setShNewRegNy(1);
		vo.setShRecommend(null);
		model.addAttribute("listNewReg", productService.selectListCarInfo(vo));
		
		return Commvar.PATH_HOME + "indexUsr";
	}
}
