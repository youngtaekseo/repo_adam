package com.ucl.infra.index;

import java.util.List;

public interface IndexDao {
	
	// 판매현황(계)
	public IndexDto selectOneSaleInfo();
	
	// 판매현황(자료수)
	public int selectOneCount();
	
	// 판매현황(리스트)
	public List<IndexDto> selectListSaleList(IndexVo vo);
}
