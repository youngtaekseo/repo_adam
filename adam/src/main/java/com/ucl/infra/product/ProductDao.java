package com.ucl.infra.product;

import java.util.List;

public interface ProductDao {
	public List<ProductDto> selectList(ProductVo vo);
	public ProductDto selectOne(ProductDto dto);
	
	public int insert(ProductDto dto);
	public int update(ProductDto dto);
	public int delete(ProductDto dto);
	
	public int updateDelNy(ProductDto dto);
	
	public int selectOneDataCount(ProductVo vo);  
	public int selectOneUsrDataCount(ProductVo vo);
	public List<ProductDto> selectListBrand();
	
	// 차량정보
	public List<ProductDto> selectListCarInfo(ProductVo vo);
	// 구매목록
	public List<ProductDto> selectListSale(ProductVo vo);
	// 찜목록
	public List<ProductDto> selectListWishList(ProductVo vo);
	// 찜삭제
	public int deleteWishList(ProductVo vo);
	// 찜내역 건수 및 합계금액
	public ProductDto selectOneWisilistCount(ProductVo vo);
}
