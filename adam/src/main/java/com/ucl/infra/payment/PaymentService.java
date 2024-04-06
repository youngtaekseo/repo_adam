package com.ucl.infra.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired
	PaymentDao dao;
	
	// 결제저장
	public int paymentInsert(PaymentDto dto) {
		return dao.paymentInsert(dto);
	}
}
