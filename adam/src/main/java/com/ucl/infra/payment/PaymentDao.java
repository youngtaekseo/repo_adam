package com.ucl.infra.payment;

public interface PaymentDao {
	
	// 결제저장
	public int paymentInsert(PaymentDto dto);
	
}
