package com.ucl.infra.review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucl.common.constants.Commvar;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {

	@Autowired
	ReviewService service;
	
	// 댓글등록
	@ResponseBody
	@RequestMapping(value = "/reviewUsrInsert")
	public Map<String, Object> reviewUsrInsert(ReviewDto dto, HttpSession httpSession) throws Exception {
		
		// 리턴변수 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		// 로그인 회원순번 설정
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));		
		
		if(service.insertReview(dto) == 1) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	// 댓글 재조회
	@RequestMapping(value = "/reviewUsrReload")
	public String reviewUsrReload(ReviewVo vo, Model model) {
		// 댓글조회
		model.addAttribute("list", service.selectList(vo));
		
		return Commvar.PATH_PRODUCT + "productUsrDetail :: reviewList";
	}
}
