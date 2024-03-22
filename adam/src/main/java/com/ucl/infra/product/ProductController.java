package com.ucl.infra.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilFunction;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	// 조회화면
	@RequestMapping(value = "/productSdmList")
	public String productSdmList(@ModelAttribute("vo") ProductVo vo, Model model) throws Exception {
		UtilFunction.setSearch(vo);
		model.addAttribute("list", service.selectList(vo));
		return Commvar.PATH_PRODUCT + "productSdmList";
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
}
