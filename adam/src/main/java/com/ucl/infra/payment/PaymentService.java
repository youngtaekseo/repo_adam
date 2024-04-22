package com.ucl.infra.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired
	PaymentDao dao;
	
	// 결제저장(카드)
	public int paymentInsert(PaymentDto dto) {
		return dao.paymentInsert(dto);
	}	
	
	// 구매등록
	public int insertProductbuy(PaymentDto dto) {
		return dao.insertProductbuy(dto);
	};
	
	// 찜삭제
	public int deleteWishlist(PaymentDto dto) {
		return dao.deleteWishlist(dto);
	};	
	
	// 영수증 조회
	public List<PaymentDto> selectListBuy(PaymentVo vo) {
		return dao.selectListBuy(vo);
	};
	
	// 영수증에 표시할 구매건수, 합계금액, 결제카드정보
	public PaymentDto selectOneCountSumCard(PaymentVo vo) {
		return dao.selectOneCountSumCard(vo);
	};	
}
