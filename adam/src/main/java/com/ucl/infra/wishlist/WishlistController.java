package com.ucl.infra.wishlist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class WishlistController {

	@Autowired
	WishlistService service;
	
	// 찜 등록
	@ResponseBody
	@RequestMapping(value = "/insertWishlist")
	public Map<String, Object> insertWishlist(WishlistVo vo, WishlistDto dto, HttpSession httpSession) {
		// 결과 담을 변수
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 로그인 사용자 순번 설정
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 등록여부 확인
		WishlistDto dto2 = service.selectOneWishlist(dto);
		
		if(dto.getMbrSeq() != null && !dto.getMbrSeq().equals("")) {
			if(dto2 == null) {
				if(service.insertWishlist(dto) == 1) {
					returnMap.put("rt", "success");
					returnMap.put("wshSeq", dto.getWshSeq());
				} else {
					returnMap.put("rt", "fail");
				}
			} else {
				// 로그인 사용자 순번 설정
				vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
				// 찜 순번
				vo.setShSeq(dto2.getWshSeq());
				if(service.deleteWishlist(vo) == 1) {
					returnMap.put("rt", "exist");
				} else {
					returnMap.put("rt", "fail");
				}
			}			
		} else {
			returnMap.put("rt", "login");
		}		
		
		return returnMap;
	}
	
	// 찜 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteWishlist")
	public Map<String, Object> deleteWishlist(WishlistVo vo, HttpSession httpSession) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		if(service.deleteWishlist(vo) == 1) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
}
