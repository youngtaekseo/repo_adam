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
	
	public int insert(MemberDto dto) {
		return dao.insert(dto);
	}
	
	public int update(MemberDto dto) {
		return dao.update(dto);
	}
	
	public int delete(MemberDto dto) {
		return dao.delete(dto);
	}
	
	public int updatePw(MemberDto dto) {
		return dao.updatePw(dto);
	}
	
	public int updateDelNy(MemberDto dto) {
		return dao.updateDelNy(dto);
	}
	
	public MemberDto selectPassword(MemberDto dto) {
		return dao.selectPassword(dto);
	}
	
	public int getCount(MemberVo vo) {
		return dao.getCount(vo);
	}	
	
	public MemberDto selectLoginId(MemberDto dto) {
		return dao.selectLoginId(dto);
	}	
	
	public MemberDto selectLoginPassword(MemberDto dto) {
		return dao.selectLoginPassword(dto);
	}	
}
