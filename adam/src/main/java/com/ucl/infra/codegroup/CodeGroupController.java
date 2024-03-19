package com.ucl.infra.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilFunction;

@Controller
public class CodeGroupController {
	
	@Autowired
	CodeGroupService service;
	
//	전체리스트
	@RequestMapping(value = "/codeGroupSdmList")
	public String codeGroupListSdm(@ModelAttribute("vo") CodeGroupVo vo, Model model) throws Exception {
		UtilFunction.setSearch(vo);
		model.addAttribute("list", service.selectList(vo));		
//		model.addAttribute("vo", vo);
		
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

}
