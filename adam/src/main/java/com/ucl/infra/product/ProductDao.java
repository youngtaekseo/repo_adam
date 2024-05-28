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
	// 차량명으로 검색
	public List<ProductDto> selectListCarName(ProductVo vo);
	// 차량상세정보
	public ProductDto selectOneCarInfo(ProductVo vo);	
	// 구매목록
	public List<ProductDto> selectListSale(ProductVo vo);
	// 찜목록
	public List<ProductDto> selectListWishList(ProductVo vo);
	// 찜삭제
	public int deleteWishList(ProductVo vo);
	// 찜내역 건수 및 합계금액
	public ProductDto selectOneWisilistCount(ProductVo vo);
	// 다중선택자료 삭제
	public int deleteList(ProductVo vo);
	//이미지갯수
	public ProductDto selectOneImageCount(ProductDto dto);
	//상품이미지조회
	public List<ProductDto> selectListImages(ProductDto dto);
}
