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
	public Map<String, Object> insertWishlist(WishlistDto dto, HttpSession httpSession) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 로그인 사용자 순번 설정
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 등록여부 확인
		if(service.selectOneCount(dto) == 0) {
			if(service.insertWishlist(dto) == 1) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		} else {
			returnMap.put("rt", "exist");
		}
		
		return returnMap;
	}
	
	// 찜 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteWishlist")
	public Map<String, Object> deleteWishlist(WishlistVo vo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(service.deleteWishlist(vo) == 1) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
}
