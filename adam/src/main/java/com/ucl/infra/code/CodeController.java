package com.ucl.infra.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@RequestMapping(value = "codeXdmList")
	public String codeXdmList(Model model) throws Exception {
		model.addAttribute("list", service.selectList());
		return "codeXdmList";
	}
	
	@RequestMapping(value = "codeView")
	public String codeView(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "codeView";
	}
	
	@RequestMapping(value = "codeCreate")
	public String codeCreate(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "codeCreate";
	}
	
	@RequestMapping(value = "codeInsert")
	public String codeInsert(CodeDto dto, Model model) throws Exception {
		service.insert(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeEdit")
	public String codeEdit(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "codeEdit";
	}
	
	@RequestMapping(value = "codeUpdate")
	public String codeInsert(CodeDto dto) throws Exception {
		service.update(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeDelOne")
	public String codeDelOne(CodeDto dto) throws Exception {
		service.udtDelOne(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeDelZero")
	public String codeDelZero(CodeDto dto) throws Exception {
		service.udtDelZero(dto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "codeDelete")
	public String codeDelete(CodeDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/codeXdmList";
	}
	
}
