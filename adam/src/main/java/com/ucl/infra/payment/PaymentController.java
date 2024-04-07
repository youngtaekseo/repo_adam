package com.ucl.infra.payment;

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
public class PaymentController {

	@Autowired
	PaymentService service;	
	
	// 결제저장
	@ResponseBody
	@RequestMapping(value = "/paymentUsrInsert")
	public Map<String, Object> paymentUsrInsert(PaymentDto dto, HttpSession httpSession) {
		
		// 리턴변수선언
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 로그인 아이디 설정
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 저장
		if(service.paymentInsert(dto) == 1) {
			
			// 구매내역 등록
			service.insertProductbuy(dto);
			
			// 찜내역 삭제
			service.deleteWishlist(dto);			
			
			returnMap.put("rt", "success");
			returnMap.put("paySeq", dto.getPaySeq());
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	// 영수증 호출
	@RequestMapping(value = "/productUsrReceipt")
	public String productUsrReceipt(PaymentVo vo, Model model, HttpSession httpSession) {
		
		// 로그인 아이디 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 영수증 표시할 구매내역 조회
		model.addAttribute("list", service.selectListBuy(vo));
		
		// 영수증에 표시할 구매건수, 합계금액, 결제카드정보 조회
		model.addAttribute("item", service.selectOneCountSumCard(vo));
		
		return Commvar.PATH_PRODUCT + "productUsrReceipt";
	}
}
