package com.ucl.infra.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

	@Autowired
	ManagerDao dao;
	
	public List<ManagerDto> selectList() {
		return dao.selectList();
	}
	
	public List<ManagerDto> selectCondtion(ManagerDto dto) {
		return dao.selectCondtion(dto);
	}
	
	public ManagerDto selectOne(ManagerDto dto) {
		return dao.selectOne(dto);
	}
	
	public int insert(ManagerDto dto) {
		return dao.insert(dto);
	}
	
	public int update(ManagerDto dto) {
		return dao.update(dto);
	}
	
	public int delete(ManagerDto dto) {
		return dao.delete(dto);
	}
	
	public int udtZero(ManagerDto dto) {
		return dao.udtZero(dto);
	}
	
	public int udtOne(ManagerDto dto) {
		return dao.udtOne(dto);
	}
}
