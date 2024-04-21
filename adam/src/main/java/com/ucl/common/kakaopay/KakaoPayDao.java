package com.ucl.common.kakaopay;

import com.ucl.infra.payment.PaymentDto;

public interface KakaoPayDao {
	
	// 결제저장(카카오페이)
	public int paymentUpdateKakaoPay(KakaoPayApprovalDto dto);
	
	// 구매등록
	public int insertProductbuy(PaymentDto dto);

}
