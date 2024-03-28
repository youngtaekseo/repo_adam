package com.ucl.infra.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ucl.common.base.BaseController;
import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilFunction;

@Controller
public class MemberController extends BaseController {
	
	@Autowired
	MemberService service;
	
	//조회화면
	@RequestMapping(value = "/memberSdmList")
	public String memberSdmList(@ModelAttribute("vo") MemberVo vo, Model model) throws Exception {
		
		UtilFunction.setSearch(vo);
		
		int rowCount = service.getCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			
			model.addAttribute("list", service.selectList(vo));
			
			/* setUrl(vo); */
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
		
		// 비밀번호 암호화
		dto.setMbrPassword(encodeBcrypt(dto.getMbrPassword(), 10));

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
		
		// 비밀번호 암호화
		if(dto.getMbrPassword() != "") {
			dto.setMbrPassword(encodeBcrypt(dto.getMbrPassword(), 10));
		}

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
	
	// 로그인 아이디, 비밀번호 확인용
	@ResponseBody
	@RequestMapping(value = "/memberSdmLoginConfirm")
	public Map<String, Object> memberSdmLoginConfirm(MemberDto dto) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		String loginChk = "";
		
		String loginId       = dto.getMbrEmail();
		String loginPassword = dto.getMbrPassword();
		
		MemberDto mDto = service.selectLoginId(dto);
		
		// 아이디 확인
		if(loginId.equals(mDto.getMbrEmail())) {
			loginChk = "success";
		} else {
			loginChk = "id";
		}
		
		// 아이디 확인 정상일때
		if(loginChk == "success") {
			if(matchesBcrypt(loginPassword, mDto.getMbrPassword(), 10)) {
				loginChk = "success";
			} else {
				loginChk = "password";
			}				
		}	
		
		returnMap.put("rt", loginChk);
		
		return returnMap;
	}
	
	// 수정, 등록화면 비밀번호 확인용
	@ResponseBody
	@RequestMapping(value = "/memberSdmPwConfirm")
	public Map<String, Object> memberSdmPassword(MemberDto dto) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 암호화
		dto.setMbrPassword(encodeBcrypt(dto.getMbrPassword(), 10));
		
		// 비밀번호 비교
		if(matchesBcrypt(dto.getMbrPwConfirm(), dto.getMbrPassword(), 10)) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;			
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
