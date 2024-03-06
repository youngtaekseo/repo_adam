package com.ucl.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {
	
	@Autowired
	CodeGroupService service;
	
	@RequestMapping(value = "/codeGroupXdmList")
	public String codeGroupXdmList(Model model) throws Exception {
		
//		List<CodeGroupDto> list = service.selectList();
//		model.addAttribute("list", list);
		
		model.addAttribute("list", service.selectList());
		
//		for(CodeGroupDto a : list) {
//			System.out.println(a.getName());
//		}
		
		return "codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupView")
	public String codeGroupView(CodeGroupDto dto, Model model) throws Exception {
		
//		System.out.println("dto.getSeq() : " + dto.getSeq());
		
		model.addAttribute("item", service.selectOne(dto));
		
		return "codeGroupView";
	}
	
	@RequestMapping(value = "/codeGroupModify")
	public String codeGroupModify(CodeGroupDto dto, Model model) throws Exception {
		
//		System.out.println("dto.getSeq() : " + dto.getSeq());
		
		model.addAttribute("item", service.selectOne(dto));
		
		return "codeGroupModify";
	}	
	
	@RequestMapping(value = "/codeGroupCreate")
	public String codeGroupCreate() throws Exception {		
		return "codeGroupCreate";
	}	
	
	@RequestMapping(value = "/codeGroupInsert")
	public String codeGroupInsert(CodeGroupDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/codeGroupXdmList";
	}	
	
	@RequestMapping(value = "/codeGroupUpdate")
	public String codeGroupUpdate(CodeGroupDto dto) throws Exception {
		service.update(dto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupDelete")
	public String codeGroupDelete(CodeGroupDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupUpDelete")
	public String codeGroupUpDelete(CodeGroupDto dto) throws Exception {
		service.upDelete(dto);
		return "redirect:/codeGroupXdmList";
	}	
	
	@RequestMapping(value = "/codeGroupUpInsert")
	public String codeGroupUpInsert(CodeGroupDto dto) throws Exception {
		service.upInsert(dto);
		return "redirect:/codeGroupXdmList";
	}		
}
