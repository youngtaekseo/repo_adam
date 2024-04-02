package com.ucl.infra.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilFunction;
import com.ucl.infra.review.ReviewService;
import com.ucl.infra.review.ReviewVo;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	ReviewService reviewService;	
	
	// 조회화면
	@RequestMapping(value = "/productSdmList")
	public String productSdmList(@ModelAttribute("vo") ProductVo vo, Model model) throws Exception {
		
		UtilFunction.setSearch(vo);
		
		int rowCount = service.selectOnetDataCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			
			model.addAttribute("list", service.selectList(vo));
			
			/* setUrl(vo); */
		};
		
		return Commvar.PATH_PRODUCT + "productSdmList";
	}
	
	// 조회화면(paging 처리용)
	@ResponseBody
	@RequestMapping(value = "/productSdmListPaging")
	public Map<String, Object> productSdmListPaging(ProductVo vo) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		int rowCount = service.selectOnetDataCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			
			returnMap.put("list", service.selectList(vo));
			returnMap.put("page", vo);
		};
		
		return returnMap;
	}	
	
	// 등록화면
	@RequestMapping(value = "/productSdmCreate")
	public String productSdmCreate(ProductDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return Commvar.PATH_PRODUCT + "productSdmCreate";
	}
	
	// 수정화면
	@RequestMapping(value = "/productSdmForm")
	public String productSdmForm(ProductDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return Commvar.PATH_PRODUCT + "productSdmForm";
	}
	
	// 입력
	@RequestMapping(value = "/productSdmInsert")
	public String productSdmInsert(ProductDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/productSdmList";
	}
	
	// 수정
	@RequestMapping(value = "/productSdmUpdate")
	public String productSdmUpdate(ProductDto dto) throws Exception {
		service.update(dto);
		return "redirect:/productSdmList";
	}
	
	// 삭제
	@RequestMapping(value = "/productSdmDelete")
	public String productSdmDelete(ProductDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/productSdmList";
	}
	
	// 삭제여부수정
	@RequestMapping(value = "/productSdmDelNy")
	public String productSdmDelNy(ProductDto dto) throws Exception {
		service.updateDelNy(dto);
		return "redirect:/productSdmList";
	}
	
	// 조회조건 및 페이징정보 포함된 url 생성
	public void setUrl(ProductVo vo) throws Exception {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("shDelNy"          , vo.getShDelNy())
				.queryParam("shOptionDate"     , vo.getShOptionDate())
				.queryParam("shDateStart"      , vo.getShDateStart())
				.queryParam("shDateEnd"        , vo.getShDateEnd())
				.queryParam("shCarType"        , vo.getShCarType())
				.queryParam("shCarBrand"       , vo.getShCarBrand())
				.queryParam("shCarColor"       , vo.getShCarColor())
				.queryParam("shRecommend"      , vo.getShRecommend())
				.queryParam("shOptionRunKm"    , vo.getShOptionRunKm())
				.queryParam("shOptionRunKmFrom", vo.getShOptionRunKmFrom())
				.queryParam("shOptionRunKmTo"  , vo.getShOptionRunKmTo())
				.queryParam("shOptionYear"     , vo.getShOptionYear())
				.queryParam("shOptionYearFrom" , vo.getShOptionYearFrom())
				.queryParam("shOptionYearTo"   , vo.getShOptionYearTo())
				.queryParam("shOptionMonthFrom", vo.getShOptionMonthFrom())
				.queryParam("shOptionMonthTo"  , vo.getShOptionMonthTo())
				.queryParam("shAccident"       , vo.getShAccident())
				.queryParam("shOption"         , vo.getShOption())
				.queryParam("shValue"          , vo.getShValue())
				.build();
		vo.setUri("&"+uri.toUriString().substring(1, uri.toUriString().length()));
	}	
	
// ============================================================================
// 사용자
// ============================================================================

	// 조회화면
	@RequestMapping(value = "/productUsrList")
	public String productUsrList(ProductDto dto, Model model) {
		model.addAttribute("list", service.selectListBrandCount(dto));
		return Commvar.PATH_PRODUCT + "productUsrList";
	}
	
	// 상세화면
	@RequestMapping(value = "/productUsrDetail")
	public String productUsrDetail(ProductVo vo, Model model) {
		model.addAttribute("item", service.selectListCarInfo(vo));
		
		ReviewVo reviewVo = new ReviewVo();
		reviewVo.setShSeq(vo.getShSeq());
		
		model.addAttribute("list", reviewService.selectList(reviewVo));	
		
		return Commvar.PATH_PRODUCT + "productUsrDetail";
	}
	
}
