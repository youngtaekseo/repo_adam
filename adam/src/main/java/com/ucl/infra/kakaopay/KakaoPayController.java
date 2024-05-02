package com.ucl.infra.kakaopay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucl.common.constants.Commvar;
import com.ucl.infra.payment.PaymentDto;
import com.ucl.infra.payment.PaymentService;
import com.ucl.infra.payment.PaymentVo;

import jakarta.servlet.http.HttpSession;

@Controller

public class KakaoPayController {
	@Autowired
	KakaoPayService service;
	
	@Autowired
	PaymentService paymentService;
	
    // 결제요청
	@ResponseBody
    @RequestMapping(value = "/kakaopay")
	public Map<String, Object> kakaopay(KakaoPayVo vo, KakaoPayDto dto, HttpSession httpSession) {
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
    	String returnUrl =  service.kakaoPayReady(vo);
    	
    	Map<String, Object> returnMap = new HashMap<>();
    	returnMap.put("rt", returnUrl);
    	
        return returnMap;
	}
    
    // 결제성공
    @RequestMapping(value = "/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token")String pg_token, PaymentVo paymentVo, PaymentDto paymentDto, KakaoPayApprovalDto dto, Model model, HttpSession httpSession) {
    	// 결제승인정보
    	dto = service.kakaoPayInfo(pg_token);
    	
    	// 결제순번
    	paymentVo.setShPaySeq(dto.getPartner_order_id());
    	paymentDto.setPaySeq(paymentVo.getShPaySeq());
		// 로그인 회원순번
    	paymentVo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
    	paymentDto.setMbrSeq(paymentVo.getShMbrSeq());
    	
    	// 구매내역 등록
    	paymentService.insertProductbuy(paymentDto);
		
		// 찜내역 삭제
    	paymentService.deleteWishlist(paymentDto);
    	
		// 영수증 표시할 구매내역 조회
		model.addAttribute("list", paymentService.selectListBuy(paymentVo));
		
		// 영수증에 표시할 구매건수, 합계금액, 결제카드정보 조회
		model.addAttribute("item", paymentService.selectOneCountSumCard(paymentVo));
		
		// 영수증에 표시할 구매건수, 합계금액, 결제카드정보 조회
		model.addAttribute("info", dto);
		
		return Commvar.PATH_PRODUCT + "productUsrReceipt";
    }     
    
    // 결제취소
    @RequestMapping(value = "/kakaopayCancel")
	public String kakaopayCancel(Model model, HttpSession httpSession) {
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
