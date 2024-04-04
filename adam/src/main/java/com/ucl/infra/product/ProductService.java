package com.ucl.infra.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	// 조회조건으로 자료 조회
	public List<ProductDto> selectList(ProductVo vo) {
		return dao.selectList(vo);
	}
	
	// 1건 자료조회
	public ProductDto selectOne(ProductDto dto) {
		return dao.selectOne(dto);
	}
	
	// 등록
	public int insert(ProductDto dto) {
		return dao.insert(dto);
	}
	
	// 수정
	public int update(ProductDto dto) {
		return dao.update(dto);
	}
	
	// 삭제
	public int delete(ProductDto dto) {
		return dao.delete(dto);
	}
	
	// 회원정보 삭제여부 수정
	public int updateDelNy(ProductDto dto) {
		return dao.updateDelNy(dto);
	}
	
	// 관리자화면 페이징위한 전체자료 건수
	public int selectOneDataCount(ProductVo vo) {
		return dao.selectOneDataCount(vo);
	}
	
	// 차량리스트 페이징위한 전체 자료건수
	public int selectOneUsrDataCount(ProductVo vo) {
		return dao.selectOneUsrDataCount(vo);
	}
	
	// 제조사 리스트
	public List<ProductDto> selectListBrand() {
		return dao.selectListBrand();
	}
	
	// 차량정보
	public List<ProductDto> selectListCarInfo(ProductVo vo) {
		return dao.selectListCarInfo(vo);
	}
	
	// 구매목록
	public List<ProductDto> selectListSale(ProductVo vo) {
		return dao.selectListSale(vo);
	}
	
	// 찜목록
	public List<ProductDto> selectListWishList(ProductVo vo){
		return dao.selectListWishList(vo);
	}
	
	// 찜삭제
	public int deleteWishList(ProductVo vo) {
		return dao.deleteWishList(vo);
	}
	
	// 찜내역 건수 및 합계금액
	public ProductDto selectOneWisilistCount(ProductVo vo) {
		return dao.selectOneWisilistCount(vo);
	}
}
