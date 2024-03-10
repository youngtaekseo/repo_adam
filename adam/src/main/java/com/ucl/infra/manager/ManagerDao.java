package com.ucl.infra.manager;

import java.util.List;

public interface ManagerDao {
	public List<ManagerDto> selectList();
	public List<ManagerDto> selectCondtion(ManagerDto dto);
	public ManagerDto selectOne(ManagerDto dto);
	
	public int insert(ManagerDto dto);
	public int update(ManagerDto dto);
	public int delete(ManagerDto dto);
	
	public int udtOne(ManagerDto dto);
	public int udtZero(ManagerDto dto);
}
