package com.ucl.infra.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public List<ProductDto> selectList(ProductVo vo) {
		return dao.selectList(vo);
	}
	
	public ProductDto selectOne(ProductDto dto) {
		return dao.selectOne(dto);
	}
	
	public int insert(ProductDto dto) {
		return dao.insert(dto);
	}
	
	public int update(ProductDto dto) {
		return dao.update(dto);
	}
	
	public int delete(ProductDto dto) {
		return dao.delete(dto);
	}
	
	public int updateDelNy(ProductDto dto) {
		return dao.updateDelNy(dto);
	}
	
	public int selectOneDataCount(ProductVo vo) {
		return dao.selectOneDataCount(vo);
	}
	
	public int selectOneUsrDataCount(ProductVo vo) {
		return dao.selectOneUsrDataCount(vo);
	}
	
	public List<ProductDto> selectListBrand() {
		return dao.selectListBrand();
	}
	
	public List<ProductDto> selectListCarInfo(ProductVo vo) {
		return dao.selectListCarInfo(vo);
	}
	
	// 구매목록
	public List<ProductDto> selectListSale(ProductVo vo) {
		return dao.selectListSale(vo);
	}
}
