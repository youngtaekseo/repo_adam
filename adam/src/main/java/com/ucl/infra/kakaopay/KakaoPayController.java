package com.ucl.infra.kakaopay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ucl.common.constants.Commvar;
import com.ucl.infra.payment.PaymentService;
import com.ucl.infra.payment.PaymentVo;

import jakarta.servlet.http.HttpSession;

@Controller

public class KakaoPayController {
	@Autowired
	KakaoPayService service;
	
	@Autowired
	PaymentService paymentService;
	
	private PaymentVo paymentVo;

    // 결제요청
    @RequestMapping(value = "/kakaopay")
	public String kakaopay(KakaoPayVo vo, KakaoPayDto dto, HttpSession httpSession) {
    	System.out.println(".................................................... kakaopay");
    	
    	// 결제종류(카카오페이)
		dto.setPayTypeCd("18");
		// 로그인 회원번호
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		// 로그인 id(이메일)
		vo.setPartner_user_id((String) httpSession.getAttribute("sessMbrEmail"));
    	// 결제순번
    	service.paymentInsertPaySeq(dto);
    	// 결제순번을 주문번호에 대입
    	vo.setPartner_order_id(dto.getPaySeq());
    	// 결제요청
        return "redirect:" + service.kakaoPayReady(vo);
	}
    
    // 결제성공
    @RequestMapping(value = "/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model, HttpSession httpSession) {
    	//model.addAttribute("item", service.kakaoPayInfo(pg_token));
    	service.kakaoPayInfo(pg_token);
    	
		// 로그인 아이디 설정
    	paymentVo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 영수증 표시할 구매내역 조회
		model.addAttribute("list", paymentService.selectListBuy(paymentVo));
		
		// 영수증에 표시할 구매건수, 합계금액, 결제카드정보 조회
		model.addAttribute("item", paymentService.selectOneCountSumCard(paymentVo));
		
		return Commvar.PATH_PRODUCT + "productUsrReceipt";
    }     
    
    // 결제취소
    @RequestMapping(value = "/kakaopayCancel")
	public String kakaopayCancel(Model model, HttpSession httpSession) {
    	System.out.println(".................................................... kakaopayCancel");
    	String sessTidString = (String) httpSession.getAttribute("sessTid");
    	if(sessTidString != null) {
    		model.addAttribute("info", service.kakaoPayCancel(httpSession));

    		// 세션삭제
        	httpSession.removeAttribute("sessTid");
        	httpSession.removeAttribute("sessTotal");
        	httpSession.removeAttribute("sessTaxFree");
        	httpSession.removeAttribute("sessVat");
        	
        	return "kakaopay/kakaopayCancelSuccess"; 
    	} else {
    		return "redirect:/kakao"; 
    	}
	}
}
