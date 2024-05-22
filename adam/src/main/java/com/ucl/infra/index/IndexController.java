package com.ucl.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilApiGet;
import com.ucl.common.util.UtilDateTime;
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
	
	// 환율정보 api key
	@Value("${koreaexim.go.kr.authkey}")
	private String koreaexim_go_kr_authkey;
	
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
		
		// 환율정보 api 호출
		StringBuilder stringBuilder = new StringBuilder();
		String curDate, curDateDash;
		
		if(UtilDateTime.houreToInteger() >= 11) {
			curDate = UtilDateTime.nowYmdString(); 
			curDateDash = UtilDateTime.nowYmdStringDash();
		} else {
			curDate = UtilDateTime.preYmdString(); 
			curDateDash = UtilDateTime.preYmdStringDash();
		}
		 
		String apiUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey="+koreaexim_go_kr_authkey+"&searchdate="+curDate+"&data=AP01";
		stringBuilder = UtilApiGet.apiGet(apiUrl);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(stringBuilder.toString());
		
		//System.out.println("node.get(\"cur_unit\").asText(): " + node.get(0).get("cur_unit").asText());
		//System.out.println("node..get(\"ttb\").asText(): " + node.get(0).get("ttb").asText());
		//System.out.println("node.get(\"cur_nm\").asText(): " + node.get(0).get("cur_nm").asText());
		
		model.addAttribute("node", node);
		model.addAttribute("searchDay", curDateDash);
			
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
