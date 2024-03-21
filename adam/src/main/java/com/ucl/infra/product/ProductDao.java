package com.ucl.infra.product;

import java.util.List;

public interface ProductDao {
	public List<ProductDto> selectList(ProductVo vo);
	public ProductDto selectOne(ProductDto dto);
	
	public int insert(ProductDto dto);
	public int update(ProductDto dto);
	public int delete(ProductDto dto);
	
	public int updateDelNy(ProductDto dto);
}
