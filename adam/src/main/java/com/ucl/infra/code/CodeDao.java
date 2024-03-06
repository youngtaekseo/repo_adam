package com.ucl.infra.code;

import java.util.List;

public interface CodeDao {
	public List<CodeDto> selectList();
	public CodeDto selectOne(CodeDto dto);
	public int insert(CodeDto dto);
	public int update(CodeDto dto);
	public int udtDelOne(CodeDto dto);
	public int udtDelZero(CodeDto dto);
	public int delete(CodeDto dto);
}
