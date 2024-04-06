package com.ucl.infra.checkout;

import java.util.List;

public interface CheckOutDao {
	// 찜목록
	public List<CheckOutDto> selectListWishList(CheckOutVo vo);
	// 찜삭제
	public int deleteWishList(CheckOutVo vo);
	// 찜내역 건수 및 합계금액
	public CheckOutDto selectOneWisilistCount(CheckOutVo vo);
}
