package com.ucl.infra.payment;

import java.util.List;

public interface PaymentDao {
	
	// 결제저장
	public int paymentInsert(PaymentDto dto);
	
	// 구매등록
	public int insertProductbuy(PaymentDto dto);
	
	// 찜삭제
	public int deleteWishlist(PaymentDto dto);
	
	// 영수증 조회
	public List<PaymentDto> selectListBuy(PaymentVo vo);
	
	// 영수증에 표시할 구매건수, 합계금액, 결제카드정보
	public PaymentDto selectOneCountSumCard(PaymentVo vo);
}
