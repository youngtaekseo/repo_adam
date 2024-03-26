package com.ucl.infra.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilFunction;
import com.ucl.infra.codegroup.CodeGroupService;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	
//	전체리스트
	@RequestMapping(value = "/codeSdmList")
	public String codeListSdm(@ModelAttribute("vo") CodeVo vo, Model model) throws Exception {
		UtilFunction.setSearch(vo);
		model.addAttribute("list", service.selectList(vo));
		model.addAttribute("listCodeGroupName", codeGroupService.selectListCodeGroup());
		return Commvar.PATH_CODE + "codeSdmList";
	}
	
//	등록화면
	@RequestMapping(value = "/codeSdmCreate")
	public String codeSdmCreate(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_CODE + "codeSdmCreate";
	}
	
//	수정화면
	@RequestMapping(value = "/codeSdmForm")
	public String codeSdmForm(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_CODE + "codeSdmForm";
	}
	
//	코드등록
	@RequestMapping(value = "/codeSdmInsert")
	public String codeSdmInsert(CodeDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/codeSdmList";
	}
	
//	코드수정
	@RequestMapping(value = "/codeSdmUpdate")
	public String codeSdmUpdate(CodeDto dto) throws Exception {
		service.update(dto);
		return "redirect:/codeSdmList";	
	}	
	
//	코드삭제
	@RequestMapping(value = "/codeSdmDelete")
	public String codeSdmDelete(CodeDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/codeSdmList";
	}
	
//	코드 삭제여부 변경
	@RequestMapping(value = "/codeSdmUdtDelNy")
	public String codeSdmUdtDelNy(CodeDto dto) throws Exception {
		service.updateDelNy(dto);
		return "redirect:/codeSdmList";
	}
	
}
