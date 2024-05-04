package com.ucl.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;
import com.ucl.infra.product.ProductService;
import com.ucl.infra.product.ProductVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	@Value("${team_project1}")
	private String teamProject1;
	
	@Value("${team_project2_usr}")
	private String teamProject2Usr;
	
	@Value("${team_project2_sdm}")
	private String teamProject2Sdm;
	
	@Autowired
	IndexService service;
	
	@Autowired
	ProductService productService;

	// 메인화면
	@RequestMapping(value = "/index")
	public String index(Model model) throws Exception {
		model.addAttribute("teamProject1"   , teamProject1);
		model.addAttribute("teamProject2Usr", teamProject2Usr);
		model.addAttribute("teamProject2Sdm", teamProject2Sdm);
		
		return Commvar.PATH_HOME + "index";
	}

	// 관리자메인
	@RequestMapping(value = "/indexSdm")
	public String indexSdm(@ModelAttribute("vo") IndexVo vo, Model model) throws Exception {
		// 판매현황(합계)
		model.addAttribute("item", service.selectOneSaleInfo());
		
		// 판매현황(자료수)
		int rowCount = service.selectOneCount();
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			
			// 판매현황(리스트)
			model.addAttribute("list", service.selectListSaleList(vo));
		};
		
		return Commvar.PATH_HOME + "indexSdm";
	}
	
	// 관리자메인-paging
	@RequestMapping(value = "/indexSdmPaging")
	public String indexSdmPaging(@ModelAttribute("vo") IndexVo vo, Model model) throws Exception {
		// 판매현황(자료수)
		int rowCount = service.selectOneCount();
		
		if(rowCount > 0) {			
			// 페이지설정
			vo.setPagingVo(rowCount);
			
			// 판매현황(리스트)
			model.addAttribute("list", service.selectListSaleList(vo));
		};
		
		return Commvar.PATH_HOME + "indexSdmAjax";
	}
	
	// 사용자메인
	@RequestMapping(value = "/indexUsr")
	public String indexUsr(ProductVo vo, Model model, HttpSession httpSession) throws Exception {
		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 추천차량
		vo.setShNewRegNy(1);
		vo.setShRecommend(17);
		model.addAttribute("listRecommend", productService.selectListCarInfo(vo));
		
		// 최근등록차량
		vo.setShNewRegNy(1);
		vo.setShRecommend(null);
		model.addAttribute("listNewReg", productService.selectListCarInfo(vo));
		
		return Commvar.PATH_HOME + "indexUsr";
	}
	
	// 추천차량 재조회
	@RequestMapping(value = "/recommandUsrReload")
	public String recommandUsrReload(ProductVo vo, Model model, HttpSession httpSession) {
		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 추천차량
		vo.setShNewRegNy(0);
		vo.setShRecommend(17);
		model.addAttribute("listRecommend", productService.selectListCarInfo(vo));
		
		return Commvar.PATH_HOME + "indexUsrRecommandAjax";
	}
	
	// 최근등록차량 재조회
	@RequestMapping(value = "/regnewUsrReload")
	public String regnewUsrReload(ProductVo vo, Model model, HttpSession httpSession) {
		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 최근등록차량
		vo.setShNewRegNy(1);
		vo.setShRecommend(null);
		model.addAttribute("listNewRegReload", productService.selectListCarInfo(vo));
		
		return Commvar.PATH_HOME + "indexUsrRegnewAjax";
	}
}
