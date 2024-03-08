package com.ucl.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {
//	CodeGroupDao codeGroupDao;
	
	@Autowired
	CodeGroupDao dao;
//	CodeGroupDao dao = new CodeGroupDao();
	
	public List<CodeGroupDto> selectList(){
		
//		List<CodeGroupDto> list = dao.selectList();
//		return list;
		
		return dao.selectList();
	}
	
	public CodeGroupDto selectOne(CodeGroupDto dto){
		
//		CodeGroupDto codeGroupDto = dao.selectOne(dto);
//		return codeGroupDto;
		
		return dao.selectOne(dto);
	}
	
	public List<CodeGroupDto> selectName(CodeGroupDto dto){
		if(dto.getName().equals("")) {
			return dao.selectList();
		} else {
			return dao.selectName(dto);	
		}
	}

	public int insert(CodeGroupDto dto){
		return dao.insert(dto);
	}	
	
	public int update(CodeGroupDto dto){
		return dao.update(dto);
	}	
	
	public int delete(CodeGroupDto dto){
		return dao.delete(dto);
	}
	
	public int udtOne(CodeGroupDto dto){
		return dao.udtOne(dto);
	}
	
	public int udtZero(CodeGroupDto dto){
		return dao.udtZero(dto);
	}	
}