package com.ucl.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class CodeGroupController {
	
	@Autowired
	CodeGroupService service;
	
//	전체리스트
	@RequestMapping(value = "/codeGroupSdmList")
	public String codeGroupListSdm(Model model) throws Exception {		
		model.addAttribute("list", service.selectList());		
		return Commvar.PATH_SDM + "codegroup/codeGroupSdmList";
	}
	
//	등록화면
	@RequestMapping(value = "/codeGroupSdmForm")
	public String codeGroupSdmForm() throws Exception {		
		return Commvar.PATH_SDM + "codegroup/codeGroupSdmForm";
	}		
	
//	조회결과
	@RequestMapping(value = "/codeGroupSdmView")
	public String codeGroupSdmView(CodeGroupDto dto, Model model) throws Exception {
		model.addAttribute("list", service.selectName(dto));
		return Commvar.PATH_SDM + "codegroup/codeGroupSdmList";
	}	
	
//	그룹코드등록
	@RequestMapping(value = "/codeGroupSdmInsert")
	public String codeGroupSdmInsert(CodeGroupDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/codeGroupSdmList";
	}	
	
	//-------------------------------------------------
	
	@RequestMapping(value = "/codeGroupView")
	public String codeGroupView(CodeGroupDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "/sdm/codegroup/codeGroupView";
	}
	
	@RequestMapping(value = "/codeGroupModify")
	public String codeGroupModify(CodeGroupDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return "/sdm/codegroup/codeGroupModify";
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
