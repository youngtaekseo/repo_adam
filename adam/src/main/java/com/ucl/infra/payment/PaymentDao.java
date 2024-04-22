package com.ucl.infra.payment;

import java.util.List;

import com.ucl.infra.kakaopay.KakaoPayApprovalDto;

public interface PaymentDao {
	
	// 결제저장(카드)
	public int paymentInsert(PaymentDto dto);
	
	// 결제저장(카카오페이)
	public int paymentUpdateKakaoPay(KakaoPayApprovalDto dto);
	
	// 구매등록
	public int insertProductbuy(PaymentDto dto);
	
	// 찜삭제
	public int deleteWishlist(PaymentDto dto);
	
	// 영수증 조회
	public List<PaymentDto> selectListBuy(PaymentVo vo);
	
	// 영수증에 표시할 구매건수, 합계금액, 결제카드정보
	public PaymentDto selectOneCountSumCard(PaymentVo vo);
}
