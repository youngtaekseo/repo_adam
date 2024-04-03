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

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController extends BaseController {
	
	@Autowired
	MemberService service;
	
	//조회화면
	@RequestMapping(value = "/memberSdmList")
	public String memberSdmList(@ModelAttribute("vo") MemberVo vo, Model model) throws Exception {
		
		UtilFunction.setSearch(vo);
		
		int rowCount = service.selectOneCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			
			model.addAttribute("list", service.selectList(vo));
			
			/* setUrl(vo); */
		};
		return Commvar.PATH_MEMBER + "memberSdmList";
	}
	
	// 코드조회
	@RequestMapping(value = "/memberSdmCodeName")
	public String memberSdmCodeName(MemberVo vo, Model model) throws Exception {
		model.addAttribute("item", service.selectListCode(vo));
		return Commvar.PATH_MEMBER + "memberSdmList";
	}	
	
	// 등록화면
	@RequestMapping(value = "/memberSdmCreate")
	public String memberSdmCreate() throws Exception {
		return Commvar.PATH_MEMBER + "memberSdmCreate";
	}
	
	// 관리자등록
	@RequestMapping(value = "/memberSdmInsert")
	public String memberSdmInsert(MemberDto dto) throws Exception {
		
		// 비밀번호 암호화
		dto.setMbrPassword(encodeBcrypt(dto.getMbrPassword(), 10));

		service.insert(dto);
		return "redirect:/memberSdmList";		
	}
	
	// 수정화면
	@RequestMapping(value = "/memberSdmForm")
	public String memberSdmForm(MemberDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return Commvar.PATH_MEMBER + "memberSdmForm";
	}
	
	// 수정
	@RequestMapping(value = "/memberSdmUpdate")
	public String memberSdmUpdate(MemberDto dto) throws Exception {
		service.update(dto);
		return "redirect:/memberSdmList";	
	}
	
	// 삭제여부수정
	@RequestMapping(value = "/memberSdmDelNy")
	public String memberSdmDelNy(MemberDto dto) throws Exception {
		service.updateDelNy(dto);
		return "redirect:/memberSdmList";
	}	
	
	// 삭제
	@RequestMapping(value = "/memberSdmDelete")
	public String memberSdmDelte(MemberDto dto) throws Exception {
		service.delete(dto);
		return "redirect:/memberSdmList";
	}	
	
	// 비밀번호수정화면
	@RequestMapping(value = "/memberSdmPass")
	public String memberSdmPass() throws Exception {
		return Commvar.PATH_MEMBER + "memberSdmPass";
	}	
	
	// 비밀번호수정
	@ResponseBody
	@RequestMapping(value = "/memberSdmUpdatePass")
	public Map<String, Object> memberSdmUpdatePass(MemberDto dto, HttpSession httpSession) throws Exception {
		
		// 결과 전달 객체
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 로그인 이메일 설정
		dto.setMbrEmail((String) httpSession.getAttribute("sessMbrEmail"));
		
		String xmbrPasswordPreIn = dto.getXmbrPasswordPre();	// 현재비밀번호
		String mbrPasswordIn     = dto.getMbrPassword();		// 새비밀번호
		String xmbrPwConfirmIn   = dto.getXmbrPwConfirm();		// 새비밀번호확인
		
		// 로그인 이메일로 자료조회
		MemberDto rtDto = service.selectOneLogin(dto);
		
		if(rtDto == null ) {
			// 입력받은 새비밀번호와 새비밀전호확인 정보 불일치
			returnMap.put("rt", "newAndnew");
		} else {
			// 입력받은 현재비밀번호와 DB에 저장된 비밀번호 일치여부 확인
			String rt = passwordOldNewCheck(xmbrPasswordPreIn, rtDto.getMbrPassword());
			
			if(rt == "success") {
				// 입력받은 현재비밀번호와 DB에 저장된 비밀번호 일치
				
				// 입력받은 새비밀번호와 새비비밀전호확인 정보 비교
				if(mbrPasswordIn.equals(xmbrPwConfirmIn)) {
					// 입력받은 새비밀번호와 새비밀전호확인 정보 일치
					
					// 회원순번 설정
					dto.setMbrSeq(rtDto.getMbrSeq());
					
					// 입력받은 새비밀번호 암호화
					dto.setMbrPassword(encodeBcrypt(dto.getMbrPassword(), 10));
					
					// 비밀번호 수정
					service.updatePassword(dto);
					
					returnMap.put("rt", "success");
				} else {
					// 입력받은 새비밀번호와 새비밀전호확인 정보 불일치
					returnMap.put("rt", "newAndnew");				
				};
			} else {
				// 입력받은 현재비밀번호와 DB에 저장된 비밀번호 불일치
				returnMap.put("rt", "oldAndDb");
			};			
		};		
		
		return returnMap;
	}
	
	// 비밀번호 수정시 비밀번호 확인
	public String passwordOldNewCheck(String passOld, String passNew) throws Exception {
		String rt = "";
		
		// 비밀번호 비교
		if(matchesBcrypt(passOld, passNew, 10)) {
			rt = "success";
		} else {
			rt = "fail";
		}
		
		return rt;
	}	
	
	// 로그인 아이디, 비밀번호 확인용
	@ResponseBody
	@RequestMapping(value = "/memberSdmLoginConfirm")
	public Map<String, Object> memberSdmLoginConfirm(MemberDto dto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		String loginPassword = dto.getMbrPassword();
		
		System.out.println("loginPassword: " + loginPassword);
		
		MemberDto rtDto = service.selectOneLogin(dto);
		
		if(rtDto != null) {
			// 비밀번호 비교
			if(matchesBcrypt(loginPassword, rtDto.getMbrPassword(), 10)) {
				returnMap.put("rt", "success");
				
				httpSession.setMaxInactiveInterval(60 * Commvar.SESSION_MINUTE_SDM); // 60second * 30 = 30minute
				httpSession.setAttribute("sessMbrSeq", rtDto.getMbrSeq());
				httpSession.setAttribute("sessMbrEmail", rtDto.getMbrEmail());
				httpSession.setAttribute("sessMbrName", rtDto.getMbrName());
			} else {
				returnMap.put("rt", "password");
			}
		} else {
			returnMap.put("rt", "id");
		}

		return returnMap;
	}
	
	// 로그인아웃 세션종료
	@ResponseBody
	@RequestMapping(value = "/memberSdmLogOut")
	public Map<String, Object> memberSdmLogOut(HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();

		httpSession.invalidate();		
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	// 수정화면 비밀번호 확인용
	@ResponseBody
	@RequestMapping(value = "/memberSdmPwConfirm")
	public Map<String, Object> memberSdmPassword(MemberDto dto) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		MemberDto rtDto = service.selectOneLogin(dto);
		
		// 신규등록 이메일 존재확인
		if(dto.getMbrSeq() == null) {
			if(rtDto != null) {
				returnMap.put("rt", "email");
			} else {
				returnMap.put("rt", passwordCheck(dto));
			}
		} else {
			returnMap.put("rt", passwordCheck(dto));
		}
		
		return returnMap;			
	}	
	
	// 신규 등록시 비밀번호 확인
	public String passwordCheck(MemberDto dto) throws Exception {
		String rt = "";
		
		// 암호화
		dto.setMbrPassword(encodeBcrypt(dto.getMbrPassword(), 10));
		
		// 비밀번호 비교
		if(matchesBcrypt(dto.getXmbrPwConfirm(), dto.getMbrPassword(), 10)) {
			rt = "success";
		} else {
			rt = "noMatch";
		}
		
		return rt;
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
	
	//=========================================================================
	// 사용자
	//=========================================================================
	
	// 사용자 회원가입
	@RequestMapping(value = "/memberUsrCreate")
	public String memberUsrCreate() {
		return Commvar.PATH_MEMBER + "memberUsrCreate";
	}
	
	// 사용자등록
	@RequestMapping(value = "/memberUsrInsert")
	public String memberUsrInsert(MemberDto dto) throws Exception {
		
		// 비밀번호 암호화
		dto.setMbrPassword(encodeBcrypt(dto.getMbrPassword(), 10));

		service.insert(dto);
		return Commvar.PATH_LOGIN + "loginUsr";		
	}	
}
