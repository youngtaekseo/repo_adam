package com.ucl.infra.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;
import com.ucl.infra.wishlist.WishlistService;
import com.ucl.infra.wishlist.WishlistVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class CheckOutController {

	@Autowired
	CheckOutService service;
	
	@Autowired
	WishlistService wishlistService;
	
	// 결제하기
	@RequestMapping(value = "/productUsrCheckOut")
	public String productUsrCheckOut(@ModelAttribute("vo") CheckOutVo vo, HttpSession httpSession, Model model) throws Exception {
		// 로그인 회원순번 설정
		vo.setShSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 자료조회
		model.addAttribute("list", service.selectListWishList(vo));
		
		// 찜내역 건수 및 합계금액
		model.addAttribute("item", service.selectOneWisilistCount(vo));
		
		return Commvar.PATH_PRODUCT + "productUsrCheckOut";
	}

	// 찜 삭제
	// ResponseBody 어노테이션은 쓰지 않습니다
	// ResponseBody 사용시 return 값의 String이 고대로 출력되는 일 발생됨
	@RequestMapping(value = "/checkoutWishlistDelete")
	public String checkoutWishlistDelete(@ModelAttribute("vo") CheckOutVo vo, WishlistVo wvo, Model model, HttpSession httpSession) throws Exception {
		
		// 찜 삭제
		wvo.setShSeq(vo.getShSeq());
		wishlistService.deleteWishlist(wvo);
		
		// 로그인 회원순번 설정
		vo.setShSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 자료조회
		model.addAttribute("list", service.selectListWishList(vo));
		
		// :: #list -> productUsrWishlist 소스에서 id값으로 이동한다
		return Commvar.PATH_PRODUCT + "productUsrCheckOut :: #list"; 
	}
	
	// 찜내역 합계조회
	// ResponseBody 어노테이션은 쓰지 않습니다
	// ResponseBody 사용시 return 값의 String이 고대로 출력되는 일 발생됨
	@RequestMapping(value = "/checkoutWishlistReload")
	public String checkoutWishlistReload(@ModelAttribute("vo") CheckOutVo vo, Model model, HttpSession httpSession) throws Exception {
		
		// 로그인 회원순번 설정
		vo.setShSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 찜내역 건수 및 합계금액
		model.addAttribute("item", service.selectOneWisilistCount(vo));
		
		// :: #list -> productUsrWishlist 소스에서 id값으로 이동한다
		return Commvar.PATH_PRODUCT + "productUsrCheckOut :: #item"; 
	}	
}
