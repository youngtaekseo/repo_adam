package com.ucl.infra.checkout;

import java.util.List;

import com.ucl.infra.product.ProductDto;

public interface CheckOutDao {
	// 찜목록
	public List<ProductDto> selectListWishList(CheckOutVo vo);
	// 찜삭제
	public int deleteWishList(CheckOutVo vo);
	// 찜내역 건수 및 합계금액
	public ProductDto selectOneWisilistCount(CheckOutVo vo);
}
