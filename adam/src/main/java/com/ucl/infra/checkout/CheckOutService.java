package com.ucl.infra.checkout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckOutService {

	@Autowired
	CheckOutDao dao;
	
	// 찜목록
	public List<CheckOutDto> selectListWishList(CheckOutVo vo){
		return dao.selectListWishList(vo);
	}
	
	// 찜삭제
	public int deleteWishList(CheckOutVo vo) {
		return dao.deleteWishList(vo);
	}
	
	// 찜내역 건수 및 합계금액
	public CheckOutDto selectOneWisilistCount(CheckOutVo vo) {
		return dao.selectOneWisilistCount(vo);
	}	
}