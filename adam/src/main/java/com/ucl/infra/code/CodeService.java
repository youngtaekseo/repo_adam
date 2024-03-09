package com.ucl.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	@Autowired
	CodeDao dao;
	
	public List<CodeDto> selectList() {
		return dao.selectList();
	}
	
	public CodeDto selectOne(CodeDto dto) {
		return dao.selectOne(dto);
	}
	
	public List<CodeDto> selectName(CodeDto dto){
		if(dto.getName().equals("") && 
	       dto.getXdateFrom().equals("") && 
	       dto.getXdateTo().equals("")) 
		{
			return dao.selectList();
		} 
		else 
		if(dto.getName().equals("") && 
		   (! dto.getXdateFrom().equals("") && 
		    ! dto.getXdateTo().equals(""))) 
		{
			if(dto.getXdateType().equals("regDt")) {
//				등록일시
				return dao.selectRegDtList(dto);
			} else {
//				수정일시
				return dao.selectModDtList(dto);	
			}
		}
		else 
		{
			return dao.selectName(dto);
		}
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
