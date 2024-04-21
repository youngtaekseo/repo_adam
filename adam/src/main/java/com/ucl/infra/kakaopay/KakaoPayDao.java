package com.ucl.infra.kakaopay;

public interface KakaoPayDao {
	
	// 카카오페이 결제등록용 순번
	public int paymentInsertPaySeq(KakaoPayDto dto);

	// 결제저장(카카오페이)
	public int paymentUpdateKakaoPay(KakaoPayApprovalDto dto);
	

}
