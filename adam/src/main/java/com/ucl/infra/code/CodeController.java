package com.ucl.infra.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
//	전체리스트
	@RequestMapping(value = "/codeSdmList")
	public String codeListSdm(Model model) throws Exception {		
		model.addAttribute("list", service.selectList());		
		return Commvar.PATH_SDM_CC + "codeSdmList";
	}
	
//	수정화면
	@RequestMapping(value = "/codeSdmForm")
	public String codeSdmForm(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_SDM_CC + "codeSdmForm";
	}
	
//	등록화면
	@RequestMapping(value = "/codeSdmCreate")
	public String codeSdmCreate(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_SDM_CC + "codeSdmCreate";
	}	
	
//	조회결과
	@RequestMapping(value = "/codeSdmView")
	public String codeSdmView(CodeDto dto, Model model) throws Exception {
		model.addAttribute("list", service.selectName(dto));
		return Commvar.PATH_SDM_CC + "codeSdmList";
	}	
	
//	그룹코드등록
	@RequestMapping(value = "/codeSdmInsert")
	public String codeSdmInsert(CodeDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/codeSdmList";
	}
	
//	그룹코드수정
	@RequestMapping(value = "/codeSdmUpdate")
	public String codeSdmUpdate(CodeDto dto) throws Exception {
		service.update(dto);
		return "redirect:/codeSdmList";	
	}	
	
//	그룹코드삭제
	@RequestMapping(value = "/codeSdmDelete")
	public String codeSdmDelete(CodeDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/codeSdmList";
	}
	
//	그룹코드 삭제여부 Y로 변경
	@RequestMapping(value = "/codeSdmUdtOne")
	public String codeSdmUdtOne(CodeDto dto) throws Exception {
		service.udtOne(dto);
		return "redirect:/codeSdmList";
	}		
	
//	그룹코드 삭제여부 N로 변경
	@RequestMapping(value = "/codeSdmUdtZero")
	public String codeSdmUdtZero(CodeDto dto) throws Exception {
		service.udtZero(dto);
		return "redirect:/codeSdmList";
	}
	
//	-------------
	
	
	@RequestMapping(value = "codeXdmList")
	public String codeXdmList(Model model) throws Exception {
		model.addAttribute("list", service.selectList());
		return "/sdm/code/codeXdmList";
	}
	
	@RequestMapping(value = "codeView")
	public String codeView(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "/sdm/code/codeView";
	}
	
	@RequestMapping(value = "codeCreate")
	public String codeCreate(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "/sdm/code/codeCreate";
	}
	
	@RequestMapping(value = "codeInsert")
	public String codeInsert(CodeDto dto, Model model) throws Exception {
		service.insert(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeEdit")
	public String codeEdit(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "/sdm/code/codeEdit";
	}
	
	@RequestMapping(value = "codeUpdate")
	public String codeInsert(CodeDto dto) throws Exception {
		service.update(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeDelOne")
	public String codeDelOne(CodeDto dto) throws Exception {
		service.udtOne(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeDelZero")
	public String codeDelZero(CodeDto dto) throws Exception {
		service.udtZero(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeDelete")
	public String codeDelete(CodeDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/codeXdmList";
	}
	
}
