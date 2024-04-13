package com.ucl.infra.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

	@Autowired
	IndexDao dao;
	
	// 판매현황(계)
	public IndexDto selectOneSaleInfo() {
		return dao.selectOneSaleInfo();
	};
	
	// 판매현황(자료수)
	public int selectOneCount() {
		return dao.selectOneCount();
	};
	
	// 판매현황(리스트)
	public List<IndexDto> selectListSaleList(IndexVo vo) {
		return dao.selectListSaleList(vo);
	};	
}
