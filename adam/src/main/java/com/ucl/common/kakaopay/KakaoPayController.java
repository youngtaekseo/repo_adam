package com.ucl.common.kakaopay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ucl.common.constants.Commvar;

import jakarta.servlet.http.HttpSession;

@Controller

public class KakaoPayController {
	@Autowired
	KakaoPayService kakaoPayService;

    // 결제요청
    @RequestMapping(value = "/kakaopay")
	public String kakaopay(KakaoPayVo vo, HttpSession httpSession) {
    	System.out.println(".................................................... kakaopay");
        return "redirect:" + kakaoPayService.kakaoPayReady(vo, httpSession);
	}
    
    // 결제성공
    @RequestMapping(value = "/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model, HttpSession httpSession) {
    	model.addAttribute("item", kakaoPayService.kakaoPayInfo(pg_token, httpSession));
    	return Commvar.PATH_PRODUCT + "productUsrReceipt";
    }     
    
    // 결제취소
    @RequestMapping(value = "/kakaopayCancel")
	public String kakaopayCancel(Model model, HttpSession httpSession) {
    	System.out.println(".................................................... kakaopayCancel");
    	String sessTidString = (String) httpSession.getAttribute("sessTid");
    	if(sessTidString != null) {
    		model.addAttribute("info", kakaoPayService.kakaoPayCancel(httpSession));

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
