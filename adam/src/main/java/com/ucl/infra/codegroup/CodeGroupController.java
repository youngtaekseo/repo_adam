package com.ucl.infra.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilFunction;

@Controller
public class CodeGroupController {
	
	@Autowired
	CodeGroupService service;
	
//	전체리스트
	@RequestMapping(value = "/codeGroupSdmList")
	public String codeGroupListSdm(@ModelAttribute("vo") CodeGroupVo vo, Model model,
			 @RequestParam(value = "page", defaultValue = "1") final int page) throws Exception {
		UtilFunction.setSearch(vo);
		
		int rowCount = service.getCount(vo);
		
		if(rowCount != 0) {			
			vo.setPagingVo(rowCount, page);
			
			model.addAttribute("list", service.selectList(vo));
			model.addAttribute("page", page);
			
			setUrl(vo);
		};
		
		return Commvar.PATH_CODE_GROUP + "codeGroupSdmList";
	}
	
//	수정화면
	@RequestMapping(value = "/codeGroupSdmForm")
	public String codeGroupSdmForm(CodeGroupDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_CODE_GROUP + "codeGroupSdmForm";
	}
	
//	등록화면
	@RequestMapping(value = "/codeGroupSdmCreate")
	public String codeGroupSdmCreate() throws Exception {
		return Commvar.PATH_CODE_GROUP + "codeGroupSdmCreate";
	}	
	
//	그룹코드등록
	@RequestMapping(value = "/codeGroupSdmInsert")
	public String codeGroupSdmInsert(CodeGroupDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/codeGroupSdmList";
	}
	
//	그룹코드수정
	@RequestMapping(value = "/codeGroupSdmUpdate")
	public String codeGroupSdmUpdate(CodeGroupDto dto) throws Exception {
		service.update(dto);
		return "redirect:/codeGroupSdmList";	
	}	
	
//	그룹코드삭제
	@RequestMapping(value = "/codeGroupSdmDelete")
	public String codeGroupSdmDelete(CodeGroupDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/codeGroupSdmList";
	}
	
//	그룹코드 삭제여부 변경         
	@RequestMapping(value = "/codeGroupSdmUdtDelNy")
	public String codeGroupSdmUdtDelNy(CodeGroupDto dto) throws Exception {
		service.updateDelNy(dto);
		return "redirect:/codeGroupSdmList";
	}
	
	// 조회조건 및 페이징정보 포함된 url 생성
	public void setUrl(CodeGroupVo vo) throws Exception {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("shDelNy"      , vo.getShDelNy())
				.queryParam("shOptionDate" , vo.getShOptionDate())
				.queryParam("shDateStart"  , vo.getShDateStart())
				.queryParam("shDateEnd"    , vo.getShDateEnd())
				.queryParam("shOptionGroup", vo.getShOptionGroup())
				.queryParam("shOption"     , vo.getShOption())
				.queryParam("shValue"      , vo.getShValue())
				.build();
		vo.setUri("&"+uri.toUriString().substring(1, uri.toUriString().length()));
	}

}
