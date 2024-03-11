package com.ucl.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	@Autowired
	CodeDao dao;
	
	public List<CodeDto> selectList(CodeVo vo) {
		return dao.selectList(vo);
	}
	
	public CodeDto selectOne(CodeDto dto) {
		return dao.selectOne(dto);
	}
	
	public List<CodeDto> selectName(CodeDto dto){
		return dao.selectRegModList(dto);
	}

	public int insert(CodeDto dto){
		return dao.insert(dto);
	}	
	
	public int update(CodeDto dto){
		return dao.update(dto);
	}	
	
	public int delete(CodeDto dto){
		return dao.delete(dto);
	}
	
	public int udtOne(CodeDto dto){
		return dao.udtOne(dto);
	}
	
	public int udtZero(CodeDto dto){
		return dao.udtZero(dto);
	}
}
