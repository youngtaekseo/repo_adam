package com.ucl.infra.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {

	@Autowired
	PaymentService service;
	
	// 결제저장
	@ResponseBody
	@RequestMapping(value = "/paymentUsrInsert")
	public Map<String, Object> paymentUsrInsert(PaymentDto dto) {
		
		// 리턴변수선언
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 저장
		if(service.paymentInsert(dto) == 1) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
}
