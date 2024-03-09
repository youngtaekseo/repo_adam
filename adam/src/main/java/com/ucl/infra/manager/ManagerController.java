package com.ucl.infra.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;

@Controller
public class ManagerController {
	
	@Autowired
	ManagerService service;
	
//	관리자전체조회
	@RequestMapping(value = "managerSdmList")
	public String managerSdmList(Model model) {
		model.addAttribute("list", service.selectList());		
		return Commvar.PATH_SDM_MG + "managerSdmList";
	}
	
//	관리자수정
	@RequestMapping(value = "/managerSdmForm")
	public String managerSdmForm(ManagerDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_SDM_MG + "managerSdmForm";
	}
	
//	관리자등록
	@RequestMapping(value = "/managerSdmCreate")
	public String managerSdmCreate(ManagerDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_SDM_MG + "managerSdmCreate";
	}	
	
//	관리자조회결과
	@RequestMapping(value = "/managerSdmView")
	public String managerSdmView(ManagerDto dto, Model model) throws Exception {
		model.addAttribute("list", service.selectName(dto));
		return Commvar.PATH_SDM_MG + "managerSdmView";
	}	
	
//	관리자등록
	@RequestMapping(value = "/managerSdmInsert")
	public String managerSdmInsert(ManagerDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/managerSdmList";
	}
	
//	관리자수정
	@RequestMapping(value = "/managerSdmUpdate")
	public String managerSdmUpdate(ManagerDto dto) throws Exception {
		service.update(dto);
		return "redirect:/managerSdmList";	
	}	
	
//	관리자삭제
	@RequestMapping(value = "/managerSdmDelete")
	public String managerSdmDelete(ManagerDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/managerSdmList";
	}
	
//	관리자 삭제여부 Y로 변경
	@RequestMapping(value = "/managerSdmUdtOne")
	public String managerSdmUdtOne(ManagerDto dto) throws Exception {
		service.udtOne(dto);
		return "redirect:/managerSdmList";
	}		
	
//	관리자 삭제여부 N로 변경
	@RequestMapping(value = "/managerSdmUdtZero")
	public String managerSdmUdtZero(ManagerDto dto) throws Exception {
		service.udtZero(dto);
		return "redirect:/managerSdmList";
	}		
}
