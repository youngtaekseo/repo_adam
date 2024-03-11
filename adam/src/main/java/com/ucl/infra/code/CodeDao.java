package com.ucl.infra.code;

import java.util.List;

public interface CodeDao {
	public List<CodeDto> selectList(CodeVo vo);
	public List<CodeDto> selectName(CodeDto dto);	
	public CodeDto selectOne(CodeDto dto);
	
//	mybatis에서 insert, update, delete 된 건수를 리턴한다
//	그래서 리턴 타입을 int로 해야한다	
	public int insert(CodeDto dto);
	public int update(CodeDto dto);
	public int delete(CodeDto dto);
	
	public int udtZero(CodeDto dto);
	public int udtOne(CodeDto dto);
	
	public List<CodeDto> selectRegModList(CodeDto dto);

}
