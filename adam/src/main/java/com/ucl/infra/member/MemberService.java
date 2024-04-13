package com.ucl.infra.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;
	
	public List<MemberDto> selectList(MemberVo vo) {
		return dao.selectList(vo);
	}
	
	public List<MemberDto> selectListCode(MemberVo vo) {
		return dao.selectListCode(vo);
	}
	
	public MemberDto selectOne(MemberDto dto) {
		return dao.selectOne(dto);
	}
	
	public MemberDto selectOneLogin(MemberDto dto) {
		return dao.selectOneLogin(dto);
	}
	
	public int selectOneCount(MemberVo vo) {
		return dao.selectOneCount(vo);
	}	
	
	public int insert(MemberDto dto) {
		return dao.insert(dto);
	}
	
	public int update(MemberDto dto) {
		return dao.update(dto);
	}
	
	public int delete(MemberDto dto) {
		return dao.delete(dto);
	}
	
	public int updatePassword(MemberDto dto) {
		return dao.updatePassword(dto);
	}
	
	public int updateDelNy(MemberDto dto) {
		return dao.updateDelNy(dto);
	}
		
	// 다중선택자료 삭제
	public int deleteList(MemberVo vo) {
		return dao.deleteList(vo);
	};	
}
