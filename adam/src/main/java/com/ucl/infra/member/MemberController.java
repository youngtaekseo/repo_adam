package com.ucl.infra.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ucl.common.constants.Commvar;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	//조회화면
	@RequestMapping(value = "/memberSdmList")
	public String memberSdmList(@ModelAttribute("vo") MemberVo vo, Model model,
			 @RequestParam(value = "page", defaultValue = "1") final int page) throws Exception {
		
		int rowCount = service.getCount(vo);
		
		if(rowCount != 0) {			
			vo.setPagingVo(rowCount, page);
			
			model.addAttribute("list", service.selectList(vo));
			model.addAttribute("page", page);
			
			setUrl(vo);
		};
		return Commvar.PATH_MEMBER + "memberSdmList";
	}
	
	// 코드조회
	@RequestMapping(value = "/memberSdmCodeName")
	public String memberSdmCodeName(MemberVo vo, Model model) {
		model.addAttribute("item", service.selectListCode(vo));
		return Commvar.PATH_MEMBER + "memberSdmList";
	}	
	
	// 등록화면
	@RequestMapping(value = "/memberSdmCreate")
	public String memberSdmCreate() {
		return Commvar.PATH_MEMBER + "memberSdmCreate";
	}
	
	// 등록
	@RequestMapping(value = "/memberSdmInsert")
	public String memberSdmInsert(MemberDto dto) {
		service.insert(dto);
		return "redirect:/memberSdmList";
	}
	
	// 수정화면
	@RequestMapping(value = "/memberSdmForm")
	public String memberSdmForm(MemberDto dto, Model model) {
		model.addAttribute("item", service.selectOne(dto));
		return Commvar.PATH_MEMBER + "memberSdmForm";
	}
	
	// 수정
	@RequestMapping(value = "/memberSdmUpdate")
	public String memberSdmUpdate(MemberDto dto) {
		service.update(dto);
		return "redirect:/memberSdmList";
	}
	
	// 삭제여부수정
	@RequestMapping(value = "/memberSdmDelNy")
	public String memberSdmDelNy(MemberDto dto) {
		service.updateDelNy(dto);
		return "redirect:/memberSdmList";
	}	
	
	// 삭제
	@RequestMapping(value = "/memberSdmDelete")
	public String memberSdmDelte(MemberDto dto) {
		service.delete(dto);
		return "redirect:/memberSdmList";
	}	
	
	// 비밀번호 확인용
	@RequestMapping(value = "/memberSdmPassword")
	public String memberSdmPassword(MemberDto dto, Model model) {
		model.addAttribute("item", service.selectPassword(dto));
		return "redirect:/memberSdmList";
	}	
	
	// 조회조건 및 페이징정보 포함된 url 생성
	public void setUrl(MemberVo vo) throws Exception {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("shDelNy"     , vo.getShDelNy())
				.queryParam("shOptionDate", vo.getShOptionDate())
				.queryParam("shDateStart" , vo.getShDateStart())
				.queryParam("shDateEnd"   , vo.getShDateEnd())
				.queryParam("shOptionType", vo.getShOptionType())
				.queryParam("shSex"       , vo.getShSex())
				.queryParam("shCdgSeq"    , vo.getShCdgSeq())
				.queryParam("shOption"    , vo.getShOption())
				.queryParam("shValue"     , vo.getShValue())
				.build();
		vo.setUri("&"+uri.toUriString().substring(1, uri.toUriString().length()));
	}
}
