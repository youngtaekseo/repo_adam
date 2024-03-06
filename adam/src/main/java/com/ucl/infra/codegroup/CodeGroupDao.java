package com.ucl.infra.codegroup;

import java.util.List;

public interface CodeGroupDao {
	public List<CodeGroupDto> selectList();
	
	public CodeGroupDto selectOne(CodeGroupDto dto);
	
//	mybatis에서 insert, update, delete 된 건수를 리턴한다
//	그래서 리턴 타입을 int로 해야한다
	public int insert(CodeGroupDto dto);
	public int update(CodeGroupDto dto);
	public int upDelete(CodeGroupDto dto);
	public int upInsert(CodeGroupDto dto);
	public int delete(CodeGroupDto dto);
}
